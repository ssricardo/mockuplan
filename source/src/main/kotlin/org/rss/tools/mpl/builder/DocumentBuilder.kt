package org.rss.tools.mpl.builder

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.rss.tools.mpl.domain.Container
import org.rss.tools.mpl.domain.Document
import org.rss.tools.mpl.domain.Element
import org.rss.tools.mpl.parsing.Parser
import org.rss.tools.mpl.parsing.grammar2.MplLexer
import org.rss.tools.mpl.parsing.grammar2.MplParser
import org.slf4j.LoggerFactory
import java.io.IOException
import java.io.InputStream


/**
 * Responsible for creating a [Document] from a text file input
 * @author ricardo saturnino
 */
object DocumentBuilder : Parser<Document> {

    private val LOGGER = LoggerFactory.getLogger(Parser::class.java)

    @Throws(IOException::class)
    override fun parse(input: InputStream): Document {
        val cStream = CharStreams.fromStream(input)
        val tokens = CommonTokenStream(MplLexer(cStream))
        val parser = MplParser(tokens)

        val v = MplVisitorImpl()

        return v.visit(parser.document())
    }

    /**
     * Implements a visitor pattern to parse an antln-based document
     */
    /*private class SmmlDocumentListener extends MplBaseListener {

		private Document result = new Document();

		private Deque<Container<? super Element>> containerQueue = new LinkedList<>();
		private Deque<ContainerContext> stateQueue = new LinkedList<>();
		private Queue<Element> lineQueue = new LinkedList<>();
		private int lastLevel = 0;

		private final Reader fileReader;

		public SmmlDocumentListener() {
			this.stateQueue.addFirst(ContainerContext.ROOT);
			this.fileReader = ServiceProvider.INSTANCE.get(Reader.class);
		}

		// --------------------- handle headers -----------------
		@Override
		public void exitTemplateLine(TemplateLineContext ctx) {
			String templateRef = ctx.fileName().getText();

			InputStream is;
			try {
				is = fileReader.read(templateRef);
				Document parentDoc = parse(is);
				result.setTemplate(parentDoc);
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
			result.getStateList().addAll(states);
//			result.setStateList(states);
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

		*/
    /** Start new line.
     * Count TAB, check if indentation changed.
     * Also responsible by adding last line element in the result.
     *//*
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
				Column cl = new Column(lb.getValue());
				getCurrentContainer().addChild(cl);
			} else {
				RowData rd = new RowData();
				rd.setData(lb.getValue());
				getCurrentContainer().addChild(rd);
			}
		}

		*/
    /** Calling when a new level (tab++) is added   *//*
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
				Column col = new Column(lb.getValue());
				if (col.children() == null) {
					col.setRowList(new ArrayList<>());
					getCurrentContainer().addChild(col);
					containerQueue.addLast((Container) col);
					stateQueue.addLast(ContainerContext.ROW);
				}
			} else if (ContainerContext.ROW.equals(currentState())) {
				throw new AppException(AppValidation.INVALID_STATE, "Row should not have children");
			}
		}

		*/
    /** Creates a section and pushes it into stack  *//*
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
			Label lb = new Label(ctx.getText().trim());
			lineQueue.add(lb);
		}

		@Override
		public void exitLineHeader(LineHeaderContext ctx) {
			int level = ctx.CURRENCY().size();
			String value = ctx.ABSP().getText().trim();

			LineHeader lh = new LineHeader(value, level);
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
			Checkbox cb = new Checkbox("");
			lineQueue.add(cb);
		}

		@Override
		public void enterList(ListContext ctx) {
			stateQueue.addLast(ContainerContext.LIST);
		}

		@Override
		public void exitList(ListContext ctx) {
			ListItem li = null;

			if (! currentState().equals(ContainerContext.LIST)) {
				throw new IllegalStateException("Invalid state while trying to process ListItem");
			}

			Label lb = (Label) lineQueue.poll();

			// checks style classes
			if (lb.getValue().contains(".")) {
				String value = lb.getValue().substring(0, lb.getValue().indexOf("."));
				li = new ListItem(value);
				li.se*tStyleClass(lb.getValue().substring(lb.getValue().indexOf(".")).replaceAll("\\.", " "));
			} else {
				new ListItem(lb.getValue());
			}

			lineQueue.add(li);
			stateQueue.removeLast();
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

	}*/

    /** Flags to control the state of parsing flow  */
    private enum class ContainerContext {
        ROOT,
        SECTION,
        TABLE,
        COLUMN,
        ROW,
        LIST
    }

}


object Root: Container<Element> {

    override fun children(): List<Element> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addChild(element: Element) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}