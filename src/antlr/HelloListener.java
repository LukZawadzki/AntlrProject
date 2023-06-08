// Generated from Hello.g4 by ANTLR 4.13.0

    package antlr;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HelloParser}.
 */
public interface HelloListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HelloParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(HelloParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(HelloParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(HelloParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(HelloParser.LineContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#commentBlock}.
	 * @param ctx the parse tree
	 */
	void enterCommentBlock(HelloParser.CommentBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#commentBlock}.
	 * @param ctx the parse tree
	 */
	void exitCommentBlock(HelloParser.CommentBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(HelloParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(HelloParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NoValueDeclare}
	 * labeled alternative in {@link HelloParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterNoValueDeclare(HelloParser.NoValueDeclareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NoValueDeclare}
	 * labeled alternative in {@link HelloParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitNoValueDeclare(HelloParser.NoValueDeclareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AssignDeclare}
	 * labeled alternative in {@link HelloParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterAssignDeclare(HelloParser.AssignDeclareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AssignDeclare}
	 * labeled alternative in {@link HelloParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitAssignDeclare(HelloParser.AssignDeclareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CastDeclare}
	 * labeled alternative in {@link HelloParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterCastDeclare(HelloParser.CastDeclareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CastDeclare}
	 * labeled alternative in {@link HelloParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitCastDeclare(HelloParser.CastDeclareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MultipleDeclare}
	 * labeled alternative in {@link HelloParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMultipleDeclare(HelloParser.MultipleDeclareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MultipleDeclare}
	 * labeled alternative in {@link HelloParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMultipleDeclare(HelloParser.MultipleDeclareContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#multipleDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterMultipleDeclaration(HelloParser.MultipleDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#multipleDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitMultipleDeclaration(HelloParser.MultipleDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#varIncrement}.
	 * @param ctx the parse tree
	 */
	void enterVarIncrement(HelloParser.VarIncrementContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#varIncrement}.
	 * @param ctx the parse tree
	 */
	void exitVarIncrement(HelloParser.VarIncrementContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#funcDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFuncDeclaration(HelloParser.FuncDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#funcDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFuncDeclaration(HelloParser.FuncDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(HelloParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(HelloParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(HelloParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(HelloParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#whileBlock}.
	 * @param ctx the parse tree
	 */
	void enterWhileBlock(HelloParser.WhileBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#whileBlock}.
	 * @param ctx the parse tree
	 */
	void exitWhileBlock(HelloParser.WhileBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#forBlock}.
	 * @param ctx the parse tree
	 */
	void enterForBlock(HelloParser.ForBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#forBlock}.
	 * @param ctx the parse tree
	 */
	void exitForBlock(HelloParser.ForBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#ifBlock}.
	 * @param ctx the parse tree
	 */
	void enterIfBlock(HelloParser.IfBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#ifBlock}.
	 * @param ctx the parse tree
	 */
	void exitIfBlock(HelloParser.IfBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BlockIf}
	 * labeled alternative in {@link HelloParser#elseIfBlock}.
	 * @param ctx the parse tree
	 */
	void enterBlockIf(HelloParser.BlockIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BlockIf}
	 * labeled alternative in {@link HelloParser#elseIfBlock}.
	 * @param ctx the parse tree
	 */
	void exitBlockIf(HelloParser.BlockIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Else}
	 * labeled alternative in {@link HelloParser#elseIfBlock}.
	 * @param ctx the parse tree
	 */
	void enterElse(HelloParser.ElseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Else}
	 * labeled alternative in {@link HelloParser#elseIfBlock}.
	 * @param ctx the parse tree
	 */
	void exitElse(HelloParser.ElseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ElseIf}
	 * labeled alternative in {@link HelloParser#elseIfBlock}.
	 * @param ctx the parse tree
	 */
	void enterElseIf(HelloParser.ElseIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ElseIf}
	 * labeled alternative in {@link HelloParser#elseIfBlock}.
	 * @param ctx the parse tree
	 */
	void exitElseIf(HelloParser.ElseIfContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#arglistDeclare}.
	 * @param ctx the parse tree
	 */
	void enterArglistDeclare(HelloParser.ArglistDeclareContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#arglistDeclare}.
	 * @param ctx the parse tree
	 */
	void exitArglistDeclare(HelloParser.ArglistDeclareContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#arglistInput}.
	 * @param ctx the parse tree
	 */
	void enterArglistInput(HelloParser.ArglistInputContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#arglistInput}.
	 * @param ctx the parse tree
	 */
	void exitArglistInput(HelloParser.ArglistInputContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#arglistOutput}.
	 * @param ctx the parse tree
	 */
	void enterArglistOutput(HelloParser.ArglistOutputContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#arglistOutput}.
	 * @param ctx the parse tree
	 */
	void exitArglistOutput(HelloParser.ArglistOutputContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVariable(HelloParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVariable(HelloParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Table}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTable(HelloParser.TableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Table}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTable(HelloParser.TableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNegation(HelloParser.NegationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNegation(HelloParser.NegationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Modulo}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterModulo(HelloParser.ModuloContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Modulo}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitModulo(HelloParser.ModuloContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FuncCallTable}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFuncCallTable(HelloParser.FuncCallTableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FuncCallTable}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFuncCallTable(HelloParser.FuncCallTableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Multiplicative}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicative(HelloParser.MultiplicativeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Multiplicative}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicative(HelloParser.MultiplicativeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Additive}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAdditive(HelloParser.AdditiveContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Additive}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAdditive(HelloParser.AdditiveContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Brackets}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBrackets(HelloParser.BracketsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Brackets}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBrackets(HelloParser.BracketsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Combination}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCombination(HelloParser.CombinationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Combination}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCombination(HelloParser.CombinationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Value}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterValue(HelloParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Value}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitValue(HelloParser.ValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Compare}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCompare(HelloParser.CompareContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Compare}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCompare(HelloParser.CompareContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FunctionCall}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(HelloParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FunctionCall}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(HelloParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Power}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPower(HelloParser.PowerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Power}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPower(HelloParser.PowerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code STRING}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void enterSTRING(HelloParser.STRINGContext ctx);
	/**
	 * Exit a parse tree produced by the {@code STRING}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void exitSTRING(HelloParser.STRINGContext ctx);
	/**
	 * Enter a parse tree produced by the {@code INT}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void enterINT(HelloParser.INTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code INT}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void exitINT(HelloParser.INTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code FLOAT}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void enterFLOAT(HelloParser.FLOATContext ctx);
	/**
	 * Exit a parse tree produced by the {@code FLOAT}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void exitFLOAT(HelloParser.FLOATContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BOOL}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void enterBOOL(HelloParser.BOOLContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BOOL}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 */
	void exitBOOL(HelloParser.BOOLContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link HelloParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssign(HelloParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link HelloParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssign(HelloParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArithmeticAssign}
	 * labeled alternative in {@link HelloParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterArithmeticAssign(HelloParser.ArithmeticAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArithmeticAssign}
	 * labeled alternative in {@link HelloParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitArithmeticAssign(HelloParser.ArithmeticAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code CastAssign}
	 * labeled alternative in {@link HelloParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterCastAssign(HelloParser.CastAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code CastAssign}
	 * labeled alternative in {@link HelloParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitCastAssign(HelloParser.CastAssignContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#multipleAssignment}.
	 * @param ctx the parse tree
	 */
	void enterMultipleAssignment(HelloParser.MultipleAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#multipleAssignment}.
	 * @param ctx the parse tree
	 */
	void exitMultipleAssignment(HelloParser.MultipleAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(HelloParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(HelloParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link HelloParser#cast}.
	 * @param ctx the parse tree
	 */
	void enterCast(HelloParser.CastContext ctx);
	/**
	 * Exit a parse tree produced by {@link HelloParser#cast}.
	 * @param ctx the parse tree
	 */
	void exitCast(HelloParser.CastContext ctx);
}