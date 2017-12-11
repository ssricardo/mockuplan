// Generated from Mpl.g4 by ANTLR 4.7

package org.rss.tools.mpl.parsing.grammar;

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
	 * Enter a parse tree produced by {@link MplParser#header}.
	 * @param ctx the parse tree
	 */
	void enterHeader(MplParser.HeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#header}.
	 * @param ctx the parse tree
	 */
	void exitHeader(MplParser.HeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#headerBody}.
	 * @param ctx the parse tree
	 */
	void enterHeaderBody(MplParser.HeaderBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#headerBody}.
	 * @param ctx the parse tree
	 */
	void exitHeaderBody(MplParser.HeaderBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#templateLine}.
	 * @param ctx the parse tree
	 */
	void enterTemplateLine(MplParser.TemplateLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#templateLine}.
	 * @param ctx the parse tree
	 */
	void exitTemplateLine(MplParser.TemplateLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#statesLine}.
	 * @param ctx the parse tree
	 */
	void enterStatesLine(MplParser.StatesLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#statesLine}.
	 * @param ctx the parse tree
	 */
	void exitStatesLine(MplParser.StatesLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#stylesLine}.
	 * @param ctx the parse tree
	 */
	void enterStylesLine(MplParser.StylesLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#stylesLine}.
	 * @param ctx the parse tree
	 */
	void exitStylesLine(MplParser.StylesLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#style}.
	 * @param ctx the parse tree
	 */
	void enterStyle(MplParser.StyleContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#style}.
	 * @param ctx the parse tree
	 */
	void exitStyle(MplParser.StyleContext ctx);
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
	/**
	 * Enter a parse tree produced by {@link MplParser#bodyLine}.
	 * @param ctx the parse tree
	 */
	void enterBodyLine(MplParser.BodyLineContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#bodyLine}.
	 * @param ctx the parse tree
	 */
	void exitBodyLine(MplParser.BodyLineContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#component}.
	 * @param ctx the parse tree
	 */
	void enterComponent(MplParser.ComponentContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#component}.
	 * @param ctx the parse tree
	 */
	void exitComponent(MplParser.ComponentContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#lineHeader}.
	 * @param ctx the parse tree
	 */
	void enterLineHeader(MplParser.LineHeaderContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#lineHeader}.
	 * @param ctx the parse tree
	 */
	void exitLineHeader(MplParser.LineHeaderContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#checkbox}.
	 * @param ctx the parse tree
	 */
	void enterCheckbox(MplParser.CheckboxContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#checkbox}.
	 * @param ctx the parse tree
	 */
	void exitCheckbox(MplParser.CheckboxContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#button}.
	 * @param ctx the parse tree
	 */
	void enterButton(MplParser.ButtonContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#button}.
	 * @param ctx the parse tree
	 */
	void exitButton(MplParser.ButtonContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#email}.
	 * @param ctx the parse tree
	 */
	void enterEmail(MplParser.EmailContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#email}.
	 * @param ctx the parse tree
	 */
	void exitEmail(MplParser.EmailContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#inputText}.
	 * @param ctx the parse tree
	 */
	void enterInputText(MplParser.InputTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#inputText}.
	 * @param ctx the parse tree
	 */
	void exitInputText(MplParser.InputTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#combobox}.
	 * @param ctx the parse tree
	 */
	void enterCombobox(MplParser.ComboboxContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#combobox}.
	 * @param ctx the parse tree
	 */
	void exitCombobox(MplParser.ComboboxContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#radio}.
	 * @param ctx the parse tree
	 */
	void enterRadio(MplParser.RadioContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#radio}.
	 * @param ctx the parse tree
	 */
	void exitRadio(MplParser.RadioContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#table}.
	 * @param ctx the parse tree
	 */
	void enterTable(MplParser.TableContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#table}.
	 * @param ctx the parse tree
	 */
	void exitTable(MplParser.TableContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(MplParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(MplParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#listItem}.
	 * @param ctx the parse tree
	 */
	void enterListItem(MplParser.ListItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#listItem}.
	 * @param ctx the parse tree
	 */
	void exitListItem(MplParser.ListItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#lineText}.
	 * @param ctx the parse tree
	 */
	void enterLineText(MplParser.LineTextContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#lineText}.
	 * @param ctx the parse tree
	 */
	void exitLineText(MplParser.LineTextContext ctx);
	/**
	 * Enter a parse tree produced by {@link MplParser#fileName}.
	 * @param ctx the parse tree
	 */
	void enterFileName(MplParser.FileNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link MplParser#fileName}.
	 * @param ctx the parse tree
	 */
	void exitFileName(MplParser.FileNameContext ctx);
}