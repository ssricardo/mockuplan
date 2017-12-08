package org.rss.tools.mpl.grammar;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.rss.tools.mpl.parsing.grammar.SmmlBaseListener;
import org.rss.tools.mpl.parsing.grammar.SmmlLexer;
import org.rss.tools.mpl.parsing.grammar.SmmlListener;
import org.rss.tools.mpl.parsing.grammar.SmmlParser;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.BodyLineContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.ButtonContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.ComboboxContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.DocumentContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.EmailContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.HeaderBodyContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.InputTextContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.LineHeaderContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.LineTextContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.ListContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.RadioContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.StatesLineContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.StyleContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.StylesLineContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.TemplateLineContext;


@DisplayName("Grammar Reading/Parsing")
public class GrammarTest {

	@Test
	@DisplayName("Correct file")
	void testGrammar() throws Exception {
		walkOnFile(new SmmlBaseListener() {

			private String template;
			private Set<String> states;
			private String firstStyle;
			private boolean readMain = false, readLabel = false;
			private String fLineHeader;
			private boolean readInputText;
			private String buttonLabel;
			private boolean item2Read = false;
			private int listItens = 0;
			private boolean readEmail = false;
			private int radioItens;
			private String combobox;
			private int headerLevel;

			//-------------- header
			
			@Override
			public void exitTemplateLine(TemplateLineContext ctx) {
				this.template = ctx.fileName().getText();
			}
			
			@Override
			public void exitStatesLine(StatesLineContext ctx) {
				states = new HashSet<>();
				for (TerminalNode node : ctx.IDENTIFIER()) {
					states.add(node.getText());
				}
			}
			
			@Override
			public void exitStylesLine(StylesLineContext ctx) {
				
			}
			
			@Override
			public void exitStyle(StyleContext ctx) {
				this.firstStyle = ctx.getText();
			}
			
			//--------------
			
			@Override
			public void exitLineText(LineTextContext ctx) {
				if (ctx.getText().equals("main")) {
					this.readMain = true;
				}
				if (ctx.getText().equals("label:")) {
					this.readLabel = true;
				}
			}
			
			@Override
			public void exitLineHeader(LineHeaderContext ctx) {
				this.fLineHeader = ctx.ABSP().getText().trim();
				this.headerLevel = ctx.CURRENCY().size();
			}
			
			@Override
			public void exitInputText(InputTextContext ctx) {
				this.readInputText = true;
			}
			
			@Override
			public void exitButton(ButtonContext ctx) {
				this.buttonLabel = ctx.IDENTIFIER().getText();
			}
			
			@Override
			public void exitRadio(RadioContext ctx) {
				this.radioItens ++;
			}
			
			@Override
			public void exitList(ListContext ctx) {
				if (ctx.listItem().getText().equals("item 2")) {
					this.item2Read = true;
				}
				this.listItens++;
			}
			
			@Override
			public void exitEmail(EmailContext ctx) {
				this.readEmail  = true;
			}
			
			@Override
			public void visitErrorNode(ErrorNode node) {
				fail(node.getText());
			}
			
			@Override
			public void enterBodyLine(BodyLineContext ctx) {
				int tabQtt = ctx.TAB().size();
				System.out.println("Tab: " + tabQtt);
			}
			
			@Override
			public void exitCombobox(ComboboxContext ctx) {
				this.combobox = ctx.IDENTIFIER().getText();
			}

			@Override
			public void exitDocument(DocumentContext ctx) {
				assertAll(
						/*() -> assertEquals("theParent", template, "Error reading fileName"),*/
						() -> assertTrue(states.contains("ab"), "Error reading first state "),
						() -> assertTrue(states.contains("ex"), "Error reading second state "),
						() -> assertEquals("blue", firstStyle, "Error reading style"),
						() -> assertTrue(readMain, "Didn't read 'main' section"),
						() -> assertTrue(readLabel, "Didn't read 'label'"),
						() -> assertEquals("meu Header", this.fLineHeader, "Incorrect line header"),
						() -> assertEquals(1, this.headerLevel, "Incorrect header level"),
						() -> assertTrue(readInputText, "Didn't read InputText"),
						() -> assertEquals("salvar", this.buttonLabel, "Incorrect button reading"),
						() -> assertTrue(item2Read, "Didn't read second list item"),
						() -> assertEquals(2, listItens, "Incorrect list itens quantity"),
						() -> assertTrue(readEmail, "Didn't read email"),
						() -> assertEquals(1, radioItens, "Incorrect radio itens quantity"),
						() -> assertEquals("Cidades", combobox, "Incorrect combobox label")
						);
			}
		});
	}
	
	@Test
	@Disabled
	public void testHeader() throws Exception {
		System.out.println("Testando headers");
		
		SmmlParser parser = readParseFile("/grammar-ok.mplf");
		
		ParseTreeWalker walker = new ParseTreeWalker();
		SmmlListener listener = new SmmlBaseListener() {
			@Override
			public void enterHeaderBody(HeaderBodyContext ctx) {				
			}
			
			@Override
			public void exitTemplateLine(TemplateLineContext ctx) {
				assertEquals("theParent", ctx.fileName().getText(), "Error reading fileName");
			}
			
			@Override
			public void exitStatesLine(StatesLineContext ctx) {
				Set<String> states = new HashSet<>();
				for (TerminalNode node : ctx.IDENTIFIER()) {
					states.add(node.getText());
				}
				assertEquals(2, states.size(), "Wrong quantity of states");
				
				Iterator<String> it = states.iterator();
				assertEquals("ab", it.next(), "Error reading first state ");
				assertEquals("ex", it.next(), "Error reading first state ");
			}

			@Override
			public void exitStyle(StyleContext ctx) {
				assertEquals("blue", ctx.getText(), "Error reading style");
			}
			
		};
		
		walker.walk(listener, parser.document());
	}

	private SmmlParser readParseFile(String fileName) throws IOException, Exception {
		URL res = getClass().getResource(fileName);
		String name = Paths.get(res.toURI()).toString();
		List<String> lines = Files.readAllLines(Paths.get(name));
		StringBuilder content = new StringBuilder();
		for (String lin : lines) {
			content.append(lin).append(System.lineSeparator());
		}
		
//		System.out.println(content);
		
		@SuppressWarnings("deprecation")
		CharStream input = new ANTLRInputStream(content.toString());
		SmmlLexer lex = new SmmlLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lex);
		SmmlParser parser = new SmmlParser(tokens);
		return parser;
	}
	
	private void walkOnFile(SmmlListener listener) throws Exception {
		SmmlParser parser = readParseFile("/grammar-ok.mplf");
		ParseTreeWalker walker = new ParseTreeWalker();
		walker.walk(listener, parser.document());
	}

	@Test
	@DisplayName("Unparsable file")
	void testIncorrectFile() throws Exception {
		SmmlParser parser = readParseFile("/grammar-error.mplf");
		ParseTreeWalker walker = new ParseTreeWalker();
		
		final List<Object> errorList = new ArrayList<>();
		
		walker.walk(new SmmlBaseListener() {
			
			@Override
			public void visitErrorNode(ErrorNode node) {
				errorList.add(node.getPayload());
				System.out.println("---------");
				System.out.println(node.getPayload());
				System.out.println(node.getText());
				System.out.println(node.getSymbol());
			}
		}, parser.document());
		
		assertTrue(errorList.size() > 0);
		
	}
}
