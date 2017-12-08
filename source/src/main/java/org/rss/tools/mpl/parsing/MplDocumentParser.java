package org.rss.tools.mpl.parsing;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.rss.tools.mpl.domain.Container;
import org.rss.tools.mpl.domain.Document;
import org.rss.tools.mpl.domain.Element;
import org.rss.tools.mpl.domain.Section;
import org.rss.tools.mpl.domain.element.Button;
import org.rss.tools.mpl.domain.element.Checkbox;
import org.rss.tools.mpl.domain.element.Column;
import org.rss.tools.mpl.domain.element.Combobox;
import org.rss.tools.mpl.domain.element.InputEmail;
import org.rss.tools.mpl.domain.element.InputText;
import org.rss.tools.mpl.domain.element.Label;
import org.rss.tools.mpl.domain.element.LineHeader;
import org.rss.tools.mpl.domain.element.ListItem;
import org.rss.tools.mpl.domain.element.Radiobox;
import org.rss.tools.mpl.domain.element.RowData;
import org.rss.tools.mpl.domain.element.Table;
import org.rss.tools.mpl.global.ServiceProvider;
import org.rss.tools.mpl.parsing.grammar.SmmlBaseListener;
import org.rss.tools.mpl.parsing.grammar.SmmlLexer;
import org.rss.tools.mpl.parsing.grammar.SmmlParser;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.BodyContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.BodyLineContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.ButtonContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.CheckboxContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.ComboboxContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.EmailContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.InputTextContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.LineHeaderContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.LineTextContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.ListContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.RadioContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.StatesLineContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.StyleContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.TableContext;
import org.rss.tools.mpl.parsing.grammar.SmmlParser.TemplateLineContext;
import org.rss.tools.mpl.reader.Reader;
import org.rss.tools.mpl.validation.AppException;
import org.rss.tools.mpl.validation.AppValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Responsible by creating a {@link Document} from an Smml file input
 * @author ricardo saturnino
 */
@SuppressWarnings("deprecation")
public class MplDocumentParser implements Parser<Document> {

	private static final Logger LOGGER = LoggerFactory.getLogger(Parser.class);

	private static MplDocumentParser instance;

	private MplDocumentParser() {
	}

	public static MplDocumentParser getInstance() {
		if (null == instance) {
			instance = new MplDocumentParser();
		}
		return instance;
	}
	
	@Override
	public Document parse(InputStream input) throws IOException {
		CharStream cStream = new ANTLRInputStream(input);
		SmmlLexer lex = new SmmlLexer(cStream);
		CommonTokenStream tokens = new CommonTokenStream(lex);
		SmmlParser parser = new SmmlParser(tokens);
		
		ParseTreeWalker walker = new ParseTreeWalker();
		SmmlDocumentListener listener = new SmmlDocumentListener();
		walker.walk(listener, parser.document());
		
		return listener.result;
	}

	/**
	 * Implements a visitor pattern to parse an antln-based document
	 */
	private class SmmlDocumentListener extends SmmlBaseListener {

		private Document result = new Document();

		private Deque<Container<? super Element>> containerQueue = new LinkedList<>();
		private Deque<ContainerContext> stateQueue = new LinkedList<>();
		private Queue<Element> lineQueue = new LinkedList<>();
		private int lastLevel = 0;

		private final Reader fileReader;
		
		public SmmlDocumentListener() {
			this.stateQueue.addFirst(ContainerContext.ROOT);
			this.fileReader = ServiceProvider.get(Reader.class);
		}

		// --------------------- handle headers -----------------
		@Override
		public void exitTemplateLine(TemplateLineContext ctx) {
			String templateRef = ctx.fileName().getText();
			
			InputStream is;
			try {
				is = fileReader.read(templateRef);
				Document parentDoc = parse(is);
				result.setTempĺate(parentDoc);
			} catch (IOException e) {
				throw new RuntimeException("Impossible to read or parse template file", e);
			}
		}

		@Override
		public void exitStatesLine(StatesLineContext ctx) {
			List<String> states = new ArrayList<String>();
			for (TerminalNode node : ctx.IDENTIFIER()) {
				states.add(node.getText().trim());
			}
			result.setStateList(states);
		}

		@Override
		public void exitStyle(StyleContext ctx) {
			if (ctx.getText() != null) {
				String[] styles = ctx.getText().split(",");
				for (String item : styles) {
					result.addStyleFile(item.trim());
				}
			}
			
		}

		// ---------------------------------- body ---------------------------------
		
		/** Start new line. 
		 * Count TAB, check if indentation changed. 
		 * Also responsible by adding last line element in the result.
		 * */
		@Override
		public void enterBodyLine(BodyLineContext ctx) {
			if (! lineQueue.isEmpty()) {
				int tabQtt = ctx.TAB().size();
				
				// Tab changed
				if (tabQtt != lastLevel) {
					int diff = tabQtt - lastLevel;
					
					// level in
					if (diff > 0) {
						if (diff > 1) {
							throw new AppException(AppValidation.INCORRECT_INDENTATION, lineQueue.peek());
						}
						addNewContainer();
					} else {
						addQueuedLine();		// add itens in current level before removing it
						for (int i = 0; i > diff; i--) {
							unpack();
						}
					}
					lastLevel = tabQtt;
				}

				addQueuedLine();
			}
		}

		private void unpack() {
			try {
				stateQueue.removeLast();
				containerQueue.removeLast();
			} catch (NoSuchElementException e) {
				throw new AppException(AppValidation.INVALID_STATE, lineQueue.peek());
			}
		}

		private void addQueuedLine() {
			while (! lineQueue.isEmpty()) {
				if (ContainerContext.SECTION.equals(currentState())) {
					getCurrentContainer().addChild(lineQueue.poll());
				} else if (ContainerContext.COLUMN.equals(currentState()) || ContainerContext.ROW.equals(currentState())) {
					addColumnOrRow();
				} else {
					throw new AppException(AppValidation.INVALID_STATE, "No valid container determined");
				}
			}
		}

		private void addColumnOrRow() {
			Label lb = checkSingleLabelGet();
			if (ContainerContext.COLUMN.equals(currentState())) {
				Column cl = new Column();
				cl.setName(lb.getValue());
				getCurrentContainer().addChild(cl);
			} else {
				RowData rd = new RowData();				
				rd.setData(lb.getValue());
				getCurrentContainer().addChild(rd);
			}
		}

		/** Calling when a new level (tab++) is added  */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void addNewContainer() {
			if (ContainerContext.ROOT.equals(currentState()) || ContainerContext.SECTION.equals(currentState())) {
				createNewSection();
			} else if (ContainerContext.TABLE.equals(currentState())) {
				Table tb = (Table) lineQueue.poll();
				getCurrentContainer().addChild(tb);
				containerQueue.addLast((Container) tb);
				stateQueue.removeLast();
				stateQueue.addLast(ContainerContext.COLUMN);
			} else if (ContainerContext.COLUMN.equals(currentState())) {
				Label lb = checkSingleLabelGet();
				Column col = new Column();
				col.setName(lb.getValue());
				if (col.getChildren() == null) {
					col.setRowList(new ArrayList<>());
					getCurrentContainer().addChild(col);
					containerQueue.addLast((Container) col);
					stateQueue.addLast(ContainerContext.ROW);
				}
			} else if (ContainerContext.ROW.equals(currentState())) {
				throw new AppException(AppValidation.INVALID_STATE, "Row should not have children");
			}
		}

		/** Creates a section and pushes it into stack */
		private void createNewSection() {
			Label lb = checkSingleLabelGet();
			Section s = new Section();
			
			// checks style class
			if (lb.getValue().contains(".")) {
				s.setId(lb.getValue().substring(0, lb.getValue().indexOf(".")));
				s.setStyleClass(lb.getValue().substring(lb.getValue().indexOf(".")).replaceAll("\\.", " "));
			} else {
				s.setId(lb.getValue());
			}
			
			// esta na raiz
			if (containerQueue.isEmpty()) {
				result.addSection(s);
			} else {
				getCurrentContainer().addChild(s);
			}
			containerQueue.addLast(s);
			stateQueue.addLast(ContainerContext.SECTION);
		}

		private Label checkSingleLabelGet() {
			if (!(lineQueue.size() == 1 && lineQueue.element() instanceof Label)) {
				throw new AppException(AppValidation.INVALID_CONTAINER_LINE, lineQueue.peek());
			}

			Label lb = (Label) lineQueue.poll();	// verified above
			return lb;
		}

		private Container<? super Element> getCurrentContainer() {
			try {
				return containerQueue.getLast();
			} catch (NoSuchElementException e) {
				throw new AppException(AppValidation.INVALID_STATE, e);
			}
		}
		
		private ContainerContext currentState() {
			try {
				return stateQueue.getLast();
			} catch (NoSuchElementException e) {
				throw new AppException(AppValidation.INVALID_STATE, e);
			}
		}

		// ------------------------ elements ----------------------------
		
		@Override
		public void exitLineText(LineTextContext ctx) {
			Label lb = new Label();
			lb.setValue(ctx.getText().trim());
			lineQueue.add(lb);
		}

		@Override
		public void exitLineHeader(LineHeaderContext ctx) {
			LineHeader lh = new LineHeader();

			lh.setLevel(ctx.CURRENCY().size());
			lh.setValue(ctx.ABSP().getText().trim());
			lineQueue.add(lh);
		}

		@Override
		public void exitInputText(InputTextContext ctx) {
			InputText it = new InputText();
			lineQueue.add(it);
		}
		
		@Override
		public void exitButton(ButtonContext ctx) {
			String label = ctx.IDENTIFIER().getText().trim();
			Button button = new Button();
			button.setValue(label);
			lineQueue.add(button);
		}
		
		@Override
		public void exitRadio(RadioContext ctx) {
			Radiobox radio = new Radiobox();
			lineQueue.add(radio);
		}
		
		@Override
		public void exitCombobox(ComboboxContext ctx) {
			Combobox cb = new Combobox();
			cb.setValue(ctx.IDENTIFIER().getText());
			lineQueue.add(cb);
		}
		
		@Override
		public void exitCheckbox(CheckboxContext ctx) {
			Checkbox cb = new Checkbox();
			lineQueue.add(cb);
		}
		
		@Override
		public void exitList(ListContext ctx) {
			ListItem li = new ListItem();
			li.setValue(ctx.listItem().getText().trim());
			lineQueue.add(li);
		}
		
		@Override
		public void exitEmail(EmailContext ctx) {
			InputEmail email = new InputEmail();
			lineQueue.add(email);
		}
		
		@Override
		public void exitTable(TableContext ctx) {
			Table tb = new Table();
			tb.setId(ctx.IDENTIFIER().getText().trim());
			lineQueue.add(tb);
			stateQueue.addLast(ContainerContext.TABLE);
		}

		@Override
		public void visitErrorNode(ErrorNode node) {
			LOGGER.warn("Error while parsing node: {}, in line {}. Trying to continue anyway...", 
					node.getText(), node.getSymbol().getLine());
			LOGGER.debug("Error symbol: {}", node.getSymbol());
		}

		@Override
		public void visitTerminal(TerminalNode node) {
			LOGGER.trace("Visiting {}", node.getText());
		}

		@Override
		public void exitBody(BodyContext ctx) {
			addQueuedLine();
		}

	}
	
	/** Flags to control the state of parsing flow */
	private enum ContainerContext {
		ROOT,
		SECTION,
		TABLE,
		COLUMN,
		ROW
	}

}
