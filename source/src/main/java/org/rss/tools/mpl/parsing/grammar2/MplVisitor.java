// Generated from D:/dev/git/mockuplan/source/src/main/java/org/rss/tools/mpl/parsing/grammar2\Mpl.g4 by ANTLR 4.7
package org.rss.tools.mpl.parsing.grammar2;


import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MplParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MplVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MplParser#document}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDocument(MplParser.DocumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link MplParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(MplParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MplParser#name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitName(MplParser.NameContext ctx);
	/**
	 * Visit a parse tree produced by {@link MplParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(MplParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MplParser#paramsBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamsBody(MplParser.ParamsBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MplParser#singleParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleParam(MplParser.SingleParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link MplParser#paramValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamValue(MplParser.ParamValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MplParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(MplParser.BodyContext ctx);
}