package org.rss.tools.mpl.render.html;

import java.io.StringReader;
import java.io.StringWriter;
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
import org.rss.tools.mpl.render.DocumentVisitor;
import org.rss.tools.mpl.render.RenderResponse;
import org.rss.tools.mpl.render.ResponseType;
import org.w3c.tidy.Tidy;

/**
 * Used to format the output created by other visitors. 
 * @author ricardo
 */
public class FormattedHtmlRenderer implements DocumentVisitor {

	private DocumentVisitor wrapped;

	public FormattedHtmlRenderer(DocumentVisitor wrapped) {
		super();
		if (wrapped == null) {
			throw new IllegalArgumentException("Source DocumentVisitor must be not null");
		}
		this.wrapped = wrapped;
	}

	public List<RenderResponse> getResult() {
		Tidy tidy = new Tidy();
		tidy.setIndentContent(true);
		tidy.setShowWarnings(false);

		StringWriter writer = new StringWriter();

		for (RenderResponse item : wrapped.getResult()) {
			if (item.getType().equals(ResponseType.StringData)) {
				tidy.parse(new StringReader((String) item.getData()), writer);
				item.setData(writer.toString());
			}
		}

		return wrapped.getResult();
	}

	public void startDocument(Document document) {
		wrapped.startDocument(document);
	}

	public void finishDocument(Document document) {
		wrapped.finishDocument(document);
	}

	public void visit(Document document) {
		wrapped.visit(document);
	}

	public void visit(Section element) {
		wrapped.visit(element);
	}

	public void visit(Button element) {
		wrapped.visit(element);
	}

	public void visit(Checkbox element) {
		wrapped.visit(element);
	}

	public void visit(InputEmail element) {
		wrapped.visit(element);
	}

	public void visit(InputSecret element) {
		wrapped.visit(element);
	}

	public void visit(InputText element) {
		wrapped.visit(element);
	}

	public void visit(Label element) {
		wrapped.visit(element);
	}

	public void visit(LineHeader element) {
		wrapped.visit(element);
	}

	public void visit(ListItem element) {
		wrapped.visit(element);
	}

	public void visit(Radiobox element) {
		wrapped.visit(element);
	}

	public void visit(Table element) {
		wrapped.visit(element);
	}

	public void visit(Container<?> element) {
		wrapped.visit(element);
	}

	public void visit(Element element) {
		wrapped.visit(element);
	}
}
