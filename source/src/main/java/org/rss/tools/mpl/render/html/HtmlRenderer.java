package org.rss.tools.mpl.render.html;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.rss.tools.mpl.domain.Container;
import org.rss.tools.mpl.domain.Document;
import org.rss.tools.mpl.domain.Element;
import org.rss.tools.mpl.domain.Section;
import org.rss.tools.mpl.domain.element.Button;
import org.rss.tools.mpl.domain.element.Checkbox;
import org.rss.tools.mpl.domain.element.Column;
import org.rss.tools.mpl.domain.element.InputEmail;
import org.rss.tools.mpl.domain.element.InputSecret;
import org.rss.tools.mpl.domain.element.InputText;
import org.rss.tools.mpl.domain.element.Label;
import org.rss.tools.mpl.domain.element.LineHeader;
import org.rss.tools.mpl.domain.element.ListItem;
import org.rss.tools.mpl.domain.element.Radiobox;
import org.rss.tools.mpl.domain.element.RowData;
import org.rss.tools.mpl.domain.element.Table;
import org.rss.tools.mpl.render.BaseDocumentRenderer;
import org.rss.tools.mpl.render.DocumentVisitor;
import org.rss.tools.mpl.render.RenderResponse;
import org.rss.tools.mpl.render.ResponseType;
import org.rss.tools.mpl.validation.AppException;
import org.rss.tools.mpl.validation.AppValidation;

/**
 * Produces an HTML String from Document objects 
 * @author ricardo saturnino
 */
public class HtmlRenderer extends BaseDocumentRenderer implements DocumentVisitor {

	private StringBuilder sbBody = new StringBuilder();

	private StringBuilder html = new StringBuilder();
	
	private List<RenderResponse> response = new ArrayList<>();

	public HtmlRenderer() {
		super();
	}

	@Override
	public void visit(Document document) {
		startDocument(document);

		for (Element element : document.getSectionList()) {
			visit(element);
		}

		finishDocument(document);
	}
	
	@Override
	public void startDocument(Document document) {
		html = new StringBuilder();
		sbBody = new StringBuilder();
		response.clear();
		response.add(new RenderResponse(ResponseType.StringData, null)); // response 0 is always the html

		String stylesPart = getStyles(document.getStyleList());

		html.append("<html><head>")
			.append(stylesPart)
			.append("</head>")
			.append(System.lineSeparator())
			.append("<body>");		
	}

	@Override
	public void finishDocument(Document document) {
		html.append(sbBody.toString())
			.append("</body>")
			.append(System.lineSeparator());

		html.append("</html>");
	}
	
	private String getStyles(List<String> styleList) {
		if (styleList.isEmpty()) {
			styleList.add(PredefinedStyle.DEFAULT.toString());
		}
		ListIterator<String> itStyles = styleList.listIterator();
		while (itStyles.hasNext()) {
			String ref = (String) itStyles.next();

			for(PredefinedStyle enumItem : PredefinedStyle.values()) {	// higher or lower cost than exception in valueOf() ?  
				if (enumItem.toString().toLowerCase().equals(ref.trim().toLowerCase())) {
					itStyles.set(enumItem.fileName);
					response.add(new RenderResponse(ResponseType.PredefinedStyle, enumItem));
				}
			}
			
		}
		
		StringBuilder sb = new StringBuilder();
		for (String item : styleList) {
			sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"")
				.append(item)
				.append("\" />");
		}
		return sb.toString();
	}

	public List<RenderResponse> getResult() {
		response.get(0).setData(html.toString());
		return response;
	}

	@Override
	protected DocumentVisitor getVisitor() {
		return this;
	}
	
	// ---------------------------- Visitings -------------------------
	
	@Override
	public void visit(Section element) {
		sbBody.append("<div ")
			.append("id=\"")
			.append(element.getId())
			.append("\" ");
		if (element.getStyleClass() != null) {
			sbBody.append("class=\"")
				.append(element.getStyleClass())
				.append("\"");
		}
		sbBody.append(">");
		
		for (Element child : element.getChildren()) {
			visit(child);
		}
		sbBody.append("</div>");
	}

	@Override
	public void visit(Button element) {
		sbBody.append("<button type=\"button\">")
			.append(element.getValue())
			.append("</button>");
	}

	@Override
	public void visit(Checkbox element) {
		sbBody.append("<input type=\"checkbox\" checked />");
	}

	@Override
	public void visit(InputText element) {
		sbBody.append("<input type=\"text\" />");
	}
	
	@Override
	public void visit(InputEmail element) {
		sbBody.append("<input type=\"email\" />");
	}

	@Override
	public void visit(InputSecret element) {
		sbBody.append("<input type=\"password\" />");
	}

	@Override
	public void visit(Label element) {
		sbBody.append("<label>")
			.append(element.getValue())
			.append("</label>");
	}

	@Override
	public void visit(LineHeader element) {
		sbBody
			.append("<h")
			.append(element.getLevel())
			.append(">")
			.append(element.getValue())
			.append("</h")
			.append(element.getLevel())
			.append(">")
			.append(System.lineSeparator());
	}

	// TODO open/close the UL
	@Override
	public void visit(ListItem element) {
		sbBody.append("<li>")
			.append(element.getValue())
			.append("</li>")
			.append(System.lineSeparator());
	}

	@Override
	public void visit(Radiobox element) {
		sbBody.append("<input type=\"radio\" />");
	}

	@Override
	public void visit(Table element) {
		if (element.getId() == null) {
			throw new AppException(AppValidation.TABLE_NO_ID);
		}
		
		sbBody.append("<table id=\"{id}\" border=\"0\"> ".replace("{id}", element.getId()));
			sbBody.append("<thead>");
			
			// invert the column -> rows to row -> columns 
			List<String[]> rowColumn = new ArrayList<>();
			for (int i = 0; i < element.getColumnList().size(); i++) {
				Column col = element.getColumnList().get(i);
				sbBody.append("<th>{name}</th>".replace("{name}", col.getName()));
				
				if (col.getRowList() != null) {
					for (int j = 0; j < col.getRowList().size(); j++) {
						RowData row = col.getRowList().get(j);
						
						if (rowColumn.size() <= j) {
							rowColumn.add(new String[element.getColumnList().size()]);
						}
						
						rowColumn.get(j)[i] = String.valueOf(row.getData());
					}
				}
			}

			sbBody.append("</thead>");

		// print rows
		for (String[] strings : rowColumn) {
			sbBody.append("<tr>");
			for (int i = 0; i < strings.length; i++) {
				sbBody
					.append("<td>")
					.append(strings[i] != null ? strings[i] : " ")
					.append("</td>");
			}
			sbBody.append("</tr>")
				.append(System.lineSeparator());
		}
		sbBody.append("</table>")
			.append(System.lineSeparator());
	}
	
	@Override
	public void visit(Container<?> element) {
	}

	/** Finds the correct decorator by the runtime instance class e redirects to the correct visit() */
	@Override
	public void visit(Element element) {
		findVisitDecorated(element);
	}
	
}