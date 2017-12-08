package org.rss.tools.mpl.rendering;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.rss.tools.mpl.domain.Document;
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
import org.rss.tools.mpl.render.DocumentVisitor;
import org.rss.tools.mpl.render.RenderResponse;
import org.rss.tools.mpl.render.RenderedFactory;
import org.rss.tools.mpl.render.RendererType;
import org.rss.tools.mpl.render.ResponseType;
import org.rss.tools.mpl.render.html.FormattedHtmlRenderer;
import org.rss.tools.mpl.render.html.HtmlRenderer;
import org.rss.tools.mpl.render.html.StatesHtmlRenderer;
import org.rss.tools.mpl.render.html.TemplateHtmlRenderer;

public class RendererTest {

	// To be improved in future
	@Test
	void testFactory() throws Exception {
		DocumentVisitor result = RenderedFactory.getInstance().getRenderer(RendererType.HTML);
		assertEquals(HtmlRenderer.class, result.getClass());
	}
	
//	@Disabled
	@Test
	void testHtmlRender() throws Exception {
		HtmlRenderer render = new HtmlRenderer();
		
		Section sec = new Section("main");
		Document doc = new Document();
		doc.addSection(sec);
		sec.getChildren().add(new Label("title"));
		sec.getChildren().add(new Button("action"));

		Section inner = new Section("inner");
		LineHeader lHeader = new LineHeader();
		lHeader.setLevel(2);
		lHeader.setValue("Clients");
		sec.addChild(lHeader);

		sec.addChild(inner);
		inner.addChild(new InputText());
		inner.addChild(new InputEmail());
		inner.addChild(new InputSecret());

		sec.addChild(new Label("check"));
		sec.addChild(new Checkbox());

		sec.addChild(new Label("radio"));
		sec.addChild(new Radiobox());
		
		ListItem li = new ListItem("Usuarios");
		sec.addChild(li);

		Table tb = new Table();
		tb.setId("datatable");
		sec.addChild(tb);
		tb.addChild(new Column("city"));
		tb.addChild(new Column("state"));
		
		
		render.visit(doc);
		String result = (String) render.getResult().get(0).getData();
		
		assertAll(
				() -> assertTrue(result.contains("<div id=\"main\""), "Section main not found"),
				() -> assertTrue(result.contains("<label>title</label>"), "Label title not found"),
				() -> assertTrue(result.contains("<button type=\"button\">action</button>"), "Button action not found"),
				() -> assertTrue(result.contains("<div id=\"inner\""), "Section inner not found"),
				() -> assertTrue(result.contains("<h2>Clients</h2>"), "Header 2 'Clients' not found"),
				() -> assertTrue(result.contains("<input type=\"text\" />"), "Input text not found"),
				() -> assertTrue(result.contains("<input type=\"email\" />"), "Input email not found"),
				() -> assertTrue(result.contains("<input type=\"password\" />"), "Input secred not found"),
				
				() -> assertTrue(result.contains("<input type=\"checkbox\" checked />"), "Checkbox not found"),
				() -> assertTrue(result.contains("<input type=\"radio\" />"), "Radio button not found"),
				() -> assertTrue(result.contains("table id=\"datatable\""), "Table#datatable not found"),
				() -> assertTrue(result.contains("<th>city</th>"), "Column city not found"),
				() -> assertTrue(result.contains("<li>Usuarios</li>"), "List item not found")
				);
		
		// print
		System.out.println(new FormattedHtmlRenderer(render).getResult());
	}
	
	@Test
	void testStates() throws Exception {
		HtmlRenderer original = new HtmlRenderer();
		
		Section sec = new Section("main");
		Document doc = new Document();
		doc.addSection(sec);
		doc.setStateList(new ArrayList<>());
		doc.getStateList().add("enter");
		doc.getStateList().add("error");
		doc.getStateList().add("done");
		
		StatesHtmlRenderer tested = new StatesHtmlRenderer(original);
		tested.visit(doc);
		
		List<RenderResponse> result = tested.getResult();
		assertEquals(3 + 1/*1 for style*/, result.size());
		
		boolean hasEnter = false, hasError = false;
		for (RenderResponse renderResponse : result) {
			if (renderResponse.getType().equals(ResponseType.StringData)) {
				if (((String) renderResponse.getData()).contains("div id=\"main\" class=\"error\"")) {
					hasError = true;
				} else if (((String) renderResponse.getData()).contains("div id=\"main\" class=\"enter\"")) {
					hasEnter = true;
				}
				System.out.println(renderResponse.getData());
			}
		}
		
		assertTrue(hasError);
		assertTrue(hasEnter);
	}
	
	@Test
	void testTemplate() throws Exception {
		HtmlRenderer original = new HtmlRenderer();

		// master template
		Section sec = new Section("main");
		Document template = new Document();
		template.addSection(sec);
		sec.getChildren().add(new Label("title"));
		sec.getChildren().add(new Button("action"));

		Section second = new Section("second");
		second.addChild(new Label("replaceThis"));
		template.addSection(second);

		// child doc
		Document curDoc = new Document();
		Section noReplace = new Section("noReplace");		
		noReplace.addChild(new Label("does not appear"));
		curDoc.addSection(noReplace);
		
		Section replaceSec = new Section("second");
		replaceSec.addChild(new Label("Clients"));
		curDoc.addSection(replaceSec);
		
		curDoc.setTempĺate(template);
		
		TemplateHtmlRenderer tested = new TemplateHtmlRenderer(original);
		tested.visit(curDoc);
		String result = (String) tested.getResult().get(0).getData();
		
		assertAll(
				() -> assertFalse(result.contains("replaceThis"), "Replace original section content"),
				() -> assertFalse(result.contains("does not appear"), "Skip section with no match id"),
				() -> assertTrue(result.contains("action"), "Keeps section not overiden"),
				() -> assertTrue(result.contains("Clients"), "Replace section with same ID")
				);
		
		System.out.println(new FormattedHtmlRenderer(tested).getResult());
	}
	
	@Test
	void testTable() throws Exception {
		HtmlRenderer render = new HtmlRenderer();
		
		Section sec = new Section("main");
		Document doc = new Document();
		doc.addSection(sec);
		
		Table tb = new Table();
		tb.setId("datatable");
		sec.addChild(tb);
		tb.addChild(new Column("city"));
		tb.addChild(new Column("state"));
		tb.getColumnList().get(0).addChild(new RowData("Sao Paulo"));
		tb.getColumnList().get(0).addChild(new RowData("Florianopolis"));
		tb.getColumnList().get(1).addChild(new RowData("Sao Paulo"));
		tb.getColumnList().get(1).addChild(new RowData("Santa Catarina"));

		tb.getColumnList().get(0).addChild(new RowData("Brasilia"));

		render.visit(doc);
		String result = (String) render.getResult().get(0).getData();

		assertAll(
				() -> assertTrue(result.contains("<td>Sao Paulo</td>")),
				() -> assertTrue(result.contains("<td>Santa Catarina</td>")),
				() -> assertTrue(result.contains("<td>Brasilia</td>"))
				);

		System.out.println(new FormattedHtmlRenderer(render).getResult());
	}

}
