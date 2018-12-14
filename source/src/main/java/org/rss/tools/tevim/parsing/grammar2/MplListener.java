// Generated from /mnt/dados/projetos/git/mockuplan/source/src/main/java/org/rss/tools/tevim/parsing/grammar2/Mpl.g4 by ANTLR 4.7
package org.rss.tools.tevim.parsing.grammar2;


import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MplParser}.
 */
public interface MplListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MplParser#document}.
	 * @param ctx the parse tree
	 */
	void enterDocument(MplParser.DocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#document}.
	 * @param ctx the parse tree
	 */
	void exitDocument(MplParser.DocumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(MplParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(MplParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(MplParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(MplParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(MplParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(MplParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#paramsBody}.
	 * @param ctx the parse tree
	 */
	void enterParamsBody(MplParser.ParamsBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#paramsBody}.
	 * @param ctx the parse tree
	 */
	void exitParamsBody(MplParser.ParamsBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#singleParam}.
	 * @param ctx the parse tree
	 */
	void enterSingleParam(MplParser.SingleParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#singleParam}.
	 * @param ctx the parse tree
	 */
	void exitSingleParam(MplParser.SingleParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#paramValue}.
	 * @param ctx the parse tree
	 */
	void enterParamValue(MplParser.ParamValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#paramValue}.
	 * @param ctx the parse tree
	 */
	void exitParamValue(MplParser.ParamValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(MplParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(MplParser.BodyContext ctx);
}