package org.rss.tools.mpl.render.html;

import java.util.ArrayList;
import java.util.List;

import org.rss.tools.mpl.domain.Container;
import org.rss.tools.mpl.domain.Document;
import org.rss.tools.mpl.domain.Element;
import org.rss.tools.mpl.domain.Section;
import org.rss.tools.mpl.domain.element.Button;
import org.rss.tools.mpl.domain.element.Checkbox;
import org.rss.tools.mpl.domain.element.InputEmail;
import org.rss.tools.mpl.domain.element.InputSecret;
import org.rss.tools.mpl.domain.element.InputText;
import org.rss.tools.mpl.domain.element.Label;
import org.rss.tools.mpl.domain.element.LineHeader;
import org.rss.tools.mpl.domain.element.ListItem;
import org.rss.tools.mpl.domain.element.Radiobox;
import org.rss.tools.mpl.domain.element.Table;
import org.rss.tools.mpl.render.BaseDocumentRenderer;
import org.rss.tools.mpl.render.DocumentVisitor;
import org.rss.tools.mpl.render.RenderResponse;

/**
 * Adds support to states from Document
 * @author ricardo saturnino
 *
 */
public class StatesHtmlRenderer extends BaseDocumentRenderer implements DocumentVisitor {

	private DocumentVisitor wrapped;

	private List<RenderResponse> responseList = new ArrayList<>();

	private String currentState;

	public StatesHtmlRenderer(DocumentVisitor wrapped) {
		super();
		if (wrapped == null) {
			throw new IllegalArgumentException("Source DocumentVisitor must be non null");
		}
		this.wrapped = wrapped;
	}

	/**
	 * For each state, calls the next DocumentVisitor and holds its result
	 */
	@Override
	public void visit(Document document) {
		if (document.getStateList() == null) {
			wrapped.visit(document);
			responseList.addAll(wrapped.getResult());
			return;
		}

		for (String state : document.getStateList()) {
			currentState = state;
			this.startDocument(document);
			for (Element element : document.getSectionList()) {
				this.visit(element);
			}
			this.finishDocument(document);
			responseList.addAll(wrapped.getResult());
		}
	}

	public List<RenderResponse> getResult() {
		return responseList;
	}

	// ------------------------------------

	@Override
	protected DocumentVisitor getVisitor() {
		return this;
	}
	
	@Override
	public void startDocument(Document document) {
		wrapped.startDocument(document);
	}

	@Override
	public void finishDocument(Document document) {
		wrapped.finishDocument(document);
	}

	/**
	 * Add css class with the name of the current state
	 */
	@Override
	public void visit(Section element) {
		String classes = element.getStyleClass();
		element.setStyleClass((classes == null) ? currentState : classes.concat(" ").concat(currentState));
		wrapped.visit(element);
		element.setStyleClass(classes);	// restore original ones
	}

	@Override
	public void visit(Button element) {
		wrapped.visit(element);
	}

	@Override
	public void visit(Checkbox element) {
		wrapped.visit(element);
	}

	@Override
	public void visit(InputEmail element) {
		wrapped.visit(element);
	}

	@Override
	public void visit(InputSecret element) {
		wrapped.visit(element);
	}

	@Override
	public void visit(InputText element) {
		wrapped.visit(element);
	}

	@Override
	public void visit(Label element) {
		wrapped.visit(element);
	}

	@Override
	public void visit(LineHeader element) {
		wrapped.visit(element);
	}

	@Override
	public void visit(ListItem element) {
		wrapped.visit(element);
	}

	@Override
	public void visit(Radiobox element) {
		wrapped.visit(element);
	}

	@Override
	public void visit(Table element) {
		wrapped.visit(element);
	}

	@Override
	public void visit(Container<?> element) {
		wrapped.visit(element);
	}

	@Override
	public void visit(Element element) {
		findVisitDecorated(element);
	}
}