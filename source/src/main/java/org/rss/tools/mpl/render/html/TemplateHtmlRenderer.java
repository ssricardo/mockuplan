package org.rss.tools.mpl.render.html;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Adds support to the use of template to Documents
 * @author ricardo saturnino
 *
 */
public class TemplateHtmlRenderer extends BaseDocumentRenderer implements DocumentVisitor {

	private final DocumentVisitor wrapped;
	
	private Map<String, Section> internalSectionMap = new HashMap<>();
	
	public TemplateHtmlRenderer(DocumentVisitor wrapped) {
		super();
		this.wrapped = wrapped;
	}

	@Override
	public List<RenderResponse> getResult() {
		return wrapped.getResult();
	}
	
	@Override
	protected DocumentVisitor getVisitor() {
		return this;
	}

	/**
	 * Prepares the sections of current Document and merges it to the template Document
	 */
	@Override
	public void visit(Document document) {
		if (document.getTempĺate() != null) {
			
			// Map main sections of wrapped document
			for (Section section : document.getSectionList()) {
				internalSectionMap.put(section.getId(), section);
			}
			
			this.startDocument(document);
			for (Element element : document.getTempĺate().getSectionList()) {
				this.visit(element);
			}
			this.finishDocument(document);
		} else {
			wrapped.visit(document);
		}
	}

	/**
	 * Prints a section or replaces the template section by the related one from child 
	 */
	@Override
	public void visit(Section element) {
		
		// instead of print data from template, prints the one related from the wrapped 
		if (internalSectionMap.containsKey(element.getId())) {
			wrapped.visit(internalSectionMap.get(element.getId()));
		} else {
			wrapped.visit(element);
		}
	}
	
	@Override
	public void startDocument(Document document) {
		wrapped.startDocument(document);
	}

	@Override
	public void finishDocument(Document document) {
		wrapped.finishDocument(document);
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
