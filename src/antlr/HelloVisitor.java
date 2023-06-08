// Generated from Hello.g4 by ANTLR 4.13.0

    package antlr;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link HelloParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface HelloVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link HelloParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(HelloParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLine(HelloParser.LineContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#commentBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommentBlock(HelloParser.CommentBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(HelloParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NoValueDeclare}
	 * labeled alternative in {@link HelloParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoValueDeclare(HelloParser.NoValueDeclareContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AssignDeclare}
	 * labeled alternative in {@link HelloParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignDeclare(HelloParser.AssignDeclareContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CastDeclare}
	 * labeled alternative in {@link HelloParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastDeclare(HelloParser.CastDeclareContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MultipleDeclare}
	 * labeled alternative in {@link HelloParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleDeclare(HelloParser.MultipleDeclareContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#multipleDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleDeclaration(HelloParser.MultipleDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#varIncrement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarIncrement(HelloParser.VarIncrementContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#funcDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDeclaration(HelloParser.FuncDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(HelloParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#funcCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCall(HelloParser.FuncCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#whileBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileBlock(HelloParser.WhileBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#forBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForBlock(HelloParser.ForBlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#ifBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfBlock(HelloParser.IfBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BlockIf}
	 * labeled alternative in {@link HelloParser#elseIfBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockIf(HelloParser.BlockIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Else}
	 * labeled alternative in {@link HelloParser#elseIfBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElse(HelloParser.ElseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ElseIf}
	 * labeled alternative in {@link HelloParser#elseIfBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseIf(HelloParser.ElseIfContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#arglistDeclare}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArglistDeclare(HelloParser.ArglistDeclareContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#arglistInput}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArglistInput(HelloParser.ArglistInputContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#arglistOutput}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArglistOutput(HelloParser.ArglistOutputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(HelloParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Table}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTable(HelloParser.TableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Negation}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNegation(HelloParser.NegationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Modulo}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModulo(HelloParser.ModuloContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FuncCallTable}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCallTable(HelloParser.FuncCallTableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Multiplicative}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicative(HelloParser.MultiplicativeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Additive}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditive(HelloParser.AdditiveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Brackets}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBrackets(HelloParser.BracketsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Combination}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCombination(HelloParser.CombinationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Value}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitValue(HelloParser.ValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Compare}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompare(HelloParser.CompareContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FunctionCall}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(HelloParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Power}
	 * labeled alternative in {@link HelloParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPower(HelloParser.PowerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code STRING}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSTRING(HelloParser.STRINGContext ctx);
	/**
	 * Visit a parse tree produced by the {@code INT}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitINT(HelloParser.INTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code FLOAT}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFLOAT(HelloParser.FLOATContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BOOL}
	 * labeled alternative in {@link HelloParser#val}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBOOL(HelloParser.BOOLContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Assign}
	 * labeled alternative in {@link HelloParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssign(HelloParser.AssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArithmeticAssign}
	 * labeled alternative in {@link HelloParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArithmeticAssign(HelloParser.ArithmeticAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code CastAssign}
	 * labeled alternative in {@link HelloParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastAssign(HelloParser.CastAssignContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#multipleAssignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipleAssignment(HelloParser.MultipleAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(HelloParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link HelloParser#cast}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCast(HelloParser.CastContext ctx);
}