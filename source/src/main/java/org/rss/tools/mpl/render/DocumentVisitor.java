package org.rss.tools.mpl.render;

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

/** Structures an object to run over Document itens */
public interface DocumentVisitor {

	List<RenderResponse> getResult();
	
	void startDocument(Document document);
	
	void finishDocument(Document document);
	
	void visit(Document document);
	
	void visit(Section element);

	void visit(Button element);

	void visit(Checkbox element);

	void visit(InputEmail element);

	void visit(InputSecret element);

	void visit(InputText element);

	void visit(Label element);

	void visit(LineHeader element);

	void visit(ListItem element);

	void visit(Radiobox element);

	void visit(Table element);

	void visit(Container<?> element);
	
	void visit(Element element);
}
