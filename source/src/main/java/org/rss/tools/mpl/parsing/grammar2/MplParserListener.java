// Generated from MplParser.g4 by ANTLR 4.7

package org.rss.tools.mpl.parsing.grammar2;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MplParserParser}.
 */
public interface MplParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MplParserParser#document}.
	 * @param ctx the parse tree
	 */
	void enterDocument(MplParserParser.DocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParserParser#document}.
	 * @param ctx the parse tree
	 */
	void exitDocument(MplParserParser.DocumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParserParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(MplParserParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParserParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(MplParserParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParserParser#name}.
	 * @param ctx the parse tree
	 */
	void enterName(MplParserParser.NameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParserParser#name}.
	 * @param ctx the parse tree
	 */
	void exitName(MplParserParser.NameContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParserParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(MplParserParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParserParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(MplParserParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParserParser#paramsBody}.
	 * @param ctx the parse tree
	 */
	void enterParamsBody(MplParserParser.ParamsBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParserParser#paramsBody}.
	 * @param ctx the parse tree
	 */
	void exitParamsBody(MplParserParser.ParamsBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParserParser#paramValue}.
	 * @param ctx the parse tree
	 */
	void enterParamValue(MplParserParser.ParamValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParserParser#paramValue}.
	 * @param ctx the parse tree
	 */
	void exitParamValue(MplParserParser.ParamValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParserParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(MplParserParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParserParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(MplParserParser.BodyContext ctx);
}