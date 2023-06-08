package expression.interpreter;

import antlr.HelloBaseVisitor;
import antlr.HelloParser;
import expression.arithmetic.*;
import expression.blocks.WhileBlock;
import expression.functions.*;
import expression.toplevel.Expr;
import expression.toplevel.Line;
import expression.blocks.Block;
import expression.blocks.ElseIfBlock;
import expression.blocks.IfBlock;
import expression.library.Print;
import expression.logic.Combination;
import expression.logic.Comparison;
import expression.logic.Negation;
import expression.toplevel.Statement;
import expression.variables.*;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public final class AntlrToExpression extends HelloBaseVisitor<Line> {
    private final List<String> varNames;
    private final List<String> semanticErrors;

    public AntlrToExpression(List<String> semanticErrors){
        this.semanticErrors = semanticErrors;
        varNames = new ArrayList<>();
    }

    @Override
    public VarName visitVariable(HelloParser.VariableContext ctx) {
        Token idToken = ctx.VARNAME().getSymbol();
        String id = ctx.VARNAME().getText();
        return new VarName(id, idToken);
    }

    @Override
    public Expr visitSTRING(HelloParser.STRINGContext ctx) {
        return new Value(ctx.getText());
    }

    @Override
    public Expr visitINT(HelloParser.INTContext ctx) {
        return new Value(ctx.getText());
    }

    @Override
    public Expr visitFLOAT(HelloParser.FLOATContext ctx) {
        return new Value(ctx.getText());
    }

    @Override
    public Expr visitBOOL(HelloParser.BOOLContext ctx) {
        return new Value(ctx.getText());
    }

    @Override
    public VarDeclaration visitNoValueDeclare(HelloParser.NoValueDeclareContext ctx) {
        Token idToken = ctx.VARNAME().getSymbol();
        String id = ctx.VARNAME().getText();
        varNames.add(id);
        return new VarDeclaration(ctx.TYPE().getText(), ctx.VARNAME().getText(), idToken);
    }

    @Override
    public Expr visitValue(HelloParser.ValueContext ctx) {
        if(ctx.val() instanceof HelloParser.STRINGContext) return visitSTRING((HelloParser.STRINGContext) ctx.val());
        else if (ctx.val() instanceof HelloParser.INTContext) return visitINT((HelloParser.INTContext) ctx.val());
        else if (ctx.val() instanceof HelloParser.FLOATContext) return visitFLOAT((HelloParser.FLOATContext) ctx.val());
        else return visitBOOL((HelloParser.BOOLContext) ctx.val());
    }

    @Override
    public VarDeclaration visitAssignDeclare(HelloParser.AssignDeclareContext ctx) {
        Token idToken = ctx.VARNAME().getSymbol();
        String id = ctx.VARNAME().getText();
        String type = ctx.TYPE().getText();
        varNames.add(id);
        return new VarDeclaration(type, id, (Expr)visit(ctx.expr()), idToken);
    }

    @Override
    public Assignment visitAssign(HelloParser.AssignContext ctx) {
        Token idToken = ctx.VARNAME().getSymbol();
        String id = ctx.VARNAME().getText();
        return new Assignment(id, (Expr) visit(ctx.getChild(2)), idToken);
    }

    @Override
    public Line visitCasting(HelloParser.CastingContext ctx) {
        Token token = ctx.getStart();
        String type = ctx.cast().TYPE().getText();
        Expr expr = (Expr) visit(ctx.expr());
        return new Casting(token, type, expr);
    }

    @Override
    public Line visitArithmeticAssign(HelloParser.ArithmeticAssignContext ctx) {
        Token idToken = ctx.VARNAME().getSymbol();
        String id = ctx.VARNAME().getText();
        VarName varName = new VarName(id, idToken);
        Expr expr = null;
        switch (ctx.getChild(1).getText()){
            case "^=" -> expr = new Power(varName, (Expr)visit(ctx.expr()), idToken);
            case "*=" -> expr = new Multiplication(varName,"*", (Expr)visit(ctx.expr()), idToken);
            case "/=" -> expr = new Multiplication(varName,"/", (Expr)visit(ctx.expr()), idToken);
            case "+=" -> expr = new Addition(varName,"+", (Expr)visit(ctx.expr()), idToken);
            case "-=" -> expr = new Addition(varName,"-", (Expr)visit(ctx.expr()), idToken);
            case "%=" -> expr = new Modulo(varName, (Expr)visit(ctx.expr()), idToken);
        }
        return new Assignment(id, expr, idToken);
    }

    @Override
    public Addition visitAdditive(HelloParser.AdditiveContext ctx) {
        Token token = ctx.getStart();
        return new Addition((Expr) visit((ctx.getChild(0))), ctx.getChild(1).getText(), (Expr) visit(ctx.getChild(2)), token);
    }

    @Override
    public Multiplication visitMultiplicative(HelloParser.MultiplicativeContext ctx) {
        Token token = ctx.getStart();
        return new Multiplication((Expr) visit((ctx.getChild(0))), ctx.getChild(1).getText(), (Expr) visit(ctx.getChild(2)), token);
    }

    @Override
    public Power visitPower(HelloParser.PowerContext ctx) {
        Token token = ctx.getStart();
        return new Power((Expr) visit((ctx.getChild(0))), (Expr) visit(ctx.getChild(2)), token);
    }

    @Override
    public Increment visitVarIncrement(HelloParser.VarIncrementContext ctx) {
        Token token = ctx.getStart();
        return new Increment(ctx.VARNAME().getText(), ctx.getChild(1).getText(), token);
    }

    @Override
    public Modulo visitModulo(HelloParser.ModuloContext ctx) {
        Token token = ctx.getStart();
        return new Modulo((Expr) visit(ctx.expr(0)), (Expr) visit(ctx.expr(1)), token);
    }

    @Override
    public Negation visitNegation(HelloParser.NegationContext ctx) {
        Token token = ctx.getStart();
        return new Negation((Expr) visit(ctx.expr()), token);
    }

    @Override
    public Combination visitCombination(HelloParser.CombinationContext ctx) {
        Token token = ctx.getStart();
        return new Combination((Expr) visit(ctx.expr(0)), ctx.getChild(1).getText(), (Expr) visit(ctx.expr(1)), token);
    }

    @Override
    public Comparison visitCompare(HelloParser.CompareContext ctx) {
        Token token = ctx.getStart();
        return new Comparison((Expr) visit(ctx.expr(0)), ctx.getChild(1).getText(), (Expr) visit(ctx.expr(1)), token);
    }

    @Override
    public Line visitStatement(HelloParser.StatementContext ctx) {
        if(!(ctx.getChild(0) instanceof HelloParser.FuncDeclarationContext) && !ctx.getChild(1).getText().equals(";")) {
            Token idToken = ctx.SEMICOLON().getSymbol();
            int line = idToken.getLine();
            int column = idToken.getCharPositionInLine()+1;
            semanticErrors.add("Error: missing semicolon ("+line+", "+column+")");
        }
        return visit(ctx.getChild(0));
    }

    @Override
    public Line visitLine(HelloParser.LineContext ctx) {
        return visit(ctx.getChild(0));
    }

    @Override
    public Block visitBlock(HelloParser.BlockContext ctx) {
        Block block = new Block(null);
        AntlrToExpression expr = new AntlrToExpression(semanticErrors);
        for(var line : ctx.line()){
            block.lines.add(expr.visit(line));
        }
        return block;
    }

    @Override
    public WhileBlock visitWhileBlock(HelloParser.WhileBlockContext ctx) {
        Token token = ctx.getStart();
        return new WhileBlock((Expr) visit(ctx.expr()), visitBlock(ctx.block()), token);
    }

    @Override
    public IfBlock visitIfBlock(HelloParser.IfBlockContext ctx) {
        Token token = ctx.getStart();
        IfBlock block = new IfBlock((Expr) visit(ctx.getChild(1)), token);
        ElseIfBlock elseBlock = (ElseIfBlock) visit(ctx.getChild(2));
        block.elseBlock = elseBlock;
        elseBlock.parentBlock = block;
        return block;
    }

    @Override
    public ElseIfBlock visitBlockIf(HelloParser.BlockIfContext ctx) {
        return new ElseIfBlock(visitBlock(ctx.block()));
    }

    @Override
    public ElseIfBlock visitElse(HelloParser.ElseContext ctx) {
        Block ifBlock = (Block) visit(ctx.block(0));
        Block elseBlock = (Block) visit(ctx.block(1));
        return new ElseIfBlock(ifBlock, elseBlock);
    }

    @Override
    public ElseIfBlock visitElseIf(HelloParser.ElseIfContext ctx) {
        Block ifBlock = (Block) visit(ctx.block());
        IfBlock elseIf = (IfBlock) visit(ctx.ifBlock());
        return new ElseIfBlock(ifBlock, elseIf);
    }

    @Override
    public Line visitBrackets(HelloParser.BracketsContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Print visitPrint(HelloParser.PrintContext ctx) {

        if(ctx.getChild(2) instanceof HelloParser.VariableContext vc) {
            Token idToken = vc.VARNAME().getSymbol();
            String id = ctx.getChild(2).getText();
            return new Print(id, null, idToken);
        }
//        else if(ctx.getChild(2) instanceof HelloParser.AdditiveContext ac) {
//            return new Print(null, (Expr) visitAdditive(ac));
//        } else if(ctx.getChild(2) instanceof HelloParser.MultiplicativeContext mc){
//            return new Print(null, (Expr) visitMultiplicative(mc));
//        } else if(ctx.getChild(2) instanceof HelloParser.PowerContext pc) {
//            return new Print(null, (Expr) visitPower(pc));
//        } else if (ctx.getChild(2) instanceof HelloParser.ValueContext s){
//            return new Print(null, visitValue(s));
//        }
        else if (ctx.getChild(2) instanceof HelloParser.ExprContext e) return new Print(null, (Expr) visit(e));

        semanticErrors.add("Error: wrong print function ("+ctx.LBRACKET().getSymbol().getLine()+")"); //I guess
        return new Print(null, null);
    }

    @Override
    public FuncDeclare visitFuncDeclaration(HelloParser.FuncDeclarationContext ctx) {
        String id = ctx.FUNCNAME().getText();
        ArgListDeclare input = visitArglistDeclare(ctx.arglistDeclare(0));
        ArgListDeclare output = visitArglistDeclare(ctx.arglistDeclare(1));
        Block block = visitBlock(ctx.block());
        Token token = ctx.getStart();
        return new FuncDeclare(id, input, output, block, token);
    }

    @Override
    public ArgListDeclare visitArglistDeclare(HelloParser.ArglistDeclareContext ctx) {
        List<VarDeclaration> vars = new ArrayList<>();
        for(HelloParser.VarDeclarationContext vdc : ctx.varDeclaration()){
            VarDeclaration declaration = (VarDeclaration) visit(vdc);
            vars.add(declaration);
        }
        return new ArgListDeclare(vars);
    }

    @Override
    public ArgListInput visitArglistInput(HelloParser.ArglistInputContext ctx) {
        List<Expr> exprs = new ArrayList<>();
        for(HelloParser.ExprContext ec : ctx.expr()){
            Expr e = (Expr) visit(ec);
            exprs.add(e);
        }
        return new ArgListInput(exprs);
    }

    @Override
    public ArgListOutput visitArglistOutput(HelloParser.ArglistOutputContext ctx) {
        String type;
        String id;
        List<Variable> variables = new ArrayList<>();
        for(int i = 1; i<ctx.getChildCount()-1; i++){
            if(!ctx.getChild(i).getText().equals(",")){
                if (ctx.getChild(i).getText().matches("int|float|bool|string|var|multiple")) {
                    type = ctx.getChild(i).getText();
                    id = ctx.getChild(i + 1).getText();
                    i++;
                } else {
                    type = null;
                    id = ctx.getChild(i).getText();
                }
                variables.add(new Variable(type, id, ctx.getStart()));
            }
        }
        return new ArgListOutput(variables);
    }

    @Override
    public Line visitFuncCall(HelloParser.FuncCallContext ctx) {
        ArgListInput input = visitArglistInput(ctx.arglistInput());
        ArgListOutput output = null;
        if(ctx.arglistOutput() != null) {
             output = visitArglistOutput(ctx.arglistOutput());
        }
        return new FuncCall(ctx.FUNCNAME().getText(),input, output, ctx.getStart());
    }
}
