// Generated from d:\dev\projetos\pml\src\main\java\org\rss\tools\smml\parsing\grammar\Smml.g4 by ANTLR 4.7

package org.rss.tools.mpl.parsing.grammar;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SmmlParser}.
 */
public interface SmmlListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SmmlParser#document}.
	 * @param ctx the parse tree
	 */
	void enterDocument(SmmlParser.DocumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#document}.
	 * @param ctx the parse tree
	 */
	void exitDocument(SmmlParser.DocumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(SmmlParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(SmmlParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#headerBody}.
	 * @param ctx the parse tree
	 */
	void enterHeaderBody(SmmlParser.HeaderBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#headerBody}.
	 * @param ctx the parse tree
	 */
	void exitHeaderBody(SmmlParser.HeaderBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#templateLine}.
	 * @param ctx the parse tree
	 */
	void enterTemplateLine(SmmlParser.TemplateLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#templateLine}.
	 * @param ctx the parse tree
	 */
	void exitTemplateLine(SmmlParser.TemplateLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#statesLine}.
	 * @param ctx the parse tree
	 */
	void enterStatesLine(SmmlParser.StatesLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#statesLine}.
	 * @param ctx the parse tree
	 */
	void exitStatesLine(SmmlParser.StatesLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#stylesLine}.
	 * @param ctx the parse tree
	 */
	void enterStylesLine(SmmlParser.StylesLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#stylesLine}.
	 * @param ctx the parse tree
	 */
	void exitStylesLine(SmmlParser.StylesLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#style}.
	 * @param ctx the parse tree
	 */
	void enterStyle(SmmlParser.StyleContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#style}.
	 * @param ctx the parse tree
	 */
	void exitStyle(SmmlParser.StyleContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(SmmlParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(SmmlParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#bodyLine}.
	 * @param ctx the parse tree
	 */
	void enterBodyLine(SmmlParser.BodyLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#bodyLine}.
	 * @param ctx the parse tree
	 */
	void exitBodyLine(SmmlParser.BodyLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#component}.
	 * @param ctx the parse tree
	 */
	void enterComponent(SmmlParser.ComponentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#component}.
	 * @param ctx the parse tree
	 */
	void exitComponent(SmmlParser.ComponentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#lineHeader}.
	 * @param ctx the parse tree
	 */
	void enterLineHeader(SmmlParser.LineHeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#lineHeader}.
	 * @param ctx the parse tree
	 */
	void exitLineHeader(SmmlParser.LineHeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#checkbox}.
	 * @param ctx the parse tree
	 */
	void enterCheckbox(SmmlParser.CheckboxContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#checkbox}.
	 * @param ctx the parse tree
	 */
	void exitCheckbox(SmmlParser.CheckboxContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#button}.
	 * @param ctx the parse tree
	 */
	void enterButton(SmmlParser.ButtonContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#button}.
	 * @param ctx the parse tree
	 */
	void exitButton(SmmlParser.ButtonContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#email}.
	 * @param ctx the parse tree
	 */
	void enterEmail(SmmlParser.EmailContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#email}.
	 * @param ctx the parse tree
	 */
	void exitEmail(SmmlParser.EmailContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#inputText}.
	 * @param ctx the parse tree
	 */
	void enterInputText(SmmlParser.InputTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#inputText}.
	 * @param ctx the parse tree
	 */
	void exitInputText(SmmlParser.InputTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#combobox}.
	 * @param ctx the parse tree
	 */
	void enterCombobox(SmmlParser.ComboboxContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#combobox}.
	 * @param ctx the parse tree
	 */
	void exitCombobox(SmmlParser.ComboboxContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#radio}.
	 * @param ctx the parse tree
	 */
	void enterRadio(SmmlParser.RadioContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#radio}.
	 * @param ctx the parse tree
	 */
	void exitRadio(SmmlParser.RadioContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#table}.
	 * @param ctx the parse tree
	 */
	void enterTable(SmmlParser.TableContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#table}.
	 * @param ctx the parse tree
	 */
	void exitTable(SmmlParser.TableContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(SmmlParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(SmmlParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#listItem}.
	 * @param ctx the parse tree
	 */
	void enterListItem(SmmlParser.ListItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#listItem}.
	 * @param ctx the parse tree
	 */
	void exitListItem(SmmlParser.ListItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#lineText}.
	 * @param ctx the parse tree
	 */
	void enterLineText(SmmlParser.LineTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#lineText}.
	 * @param ctx the parse tree
	 */
	void exitLineText(SmmlParser.LineTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link SmmlParser#fileName}.
	 * @param ctx the parse tree
	 */
	void enterFileName(SmmlParser.FileNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link SmmlParser#fileName}.
	 * @param ctx the parse tree
	 */
	void exitFileName(SmmlParser.FileNameContext ctx);
}