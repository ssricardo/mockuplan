package rendering

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.rss.tools.mpl.domain.Document
import org.rss.tools.mpl.domain.Section
import org.rss.tools.mpl.domain.element.*
import org.rss.tools.mpl.render.RenderedFactory
import org.rss.tools.mpl.render.RendererType
import org.rss.tools.mpl.render.ResponseType
import org.rss.tools.mpl.render.html.FormattedHtmlRenderer
import org.rss.tools.mpl.render.html.HtmlRenderer
import org.rss.tools.mpl.render.html.StatesHtmlRenderer
import org.rss.tools.mpl.render.html.TemplateHtmlRenderer
import java.util.*

class RendererTest {

    // To be improved in future
    @Test
    @Throws(Exception::class)
    internal fun testFactory() {
        val result = RenderedFactory.instance.getRenderer(RendererType.HTML)
        assertEquals(HtmlRenderer::class.java, result.javaClass)
    }

    //	@Disabled
    @Test
    @Throws(Exception::class)
    internal fun testHtmlRender() {
        val render = HtmlRenderer()

        val sec = Section("main", ArrayList())
        val doc = Document()
        doc.addSection(sec)
        sec.children.add(Label("title"))
        sec.children.add(Button("action"))

        val inner = Section("inner", ArrayList())
        val lHeader = LineHeader("Clients", 2)
        lHeader.level = 2
        sec.addChild(lHeader)

        sec.addChild(inner)
        inner.addChild(InputText())
        inner.addChild(InputEmail())
        inner.addChild(InputSecret())

        sec.addChild(Label("check"))
        sec.addChild(Checkbox(""))

        sec.addChild(Label("radio"))
        sec.addChild(Radiobox())

        val li = ListItem("Usuarios")
        sec.addChild(li)

        val tb = Table()
        tb.id = "datatable"
        sec.addChild(tb)
        tb.addChild(Column("city"))
        tb.addChild(Column("state"))


        render.visit(doc)
        val result = render.result[0].data as String?
        // print
        println(FormattedHtmlRenderer(render).result[0].data)

        assertAll(
                Executable { assertTrue(result!!.contains("<div id=\"main\""), "Section main not found") },
                Executable { assertTrue(result!!.contains("<label>title</label>"), "Label title not found") },
                Executable { assertTrue(result!!.contains("<button type=\"button\">action</button>"), "Button action not found") },
                Executable { assertTrue(result!!.contains("<div id=\"inner\""), "Section inner not found") },
                Executable { assertTrue(result!!.contains("<h2>Clients</h2>"), "Header 2 'Clients' not found") },
                Executable { assertTrue(result!!.contains("<input type=\"text\" />"), "Input text not found") },
                Executable { assertTrue(result!!.contains("<input type=\"email\" />"), "Input email not found") },
                Executable { assertTrue(result!!.contains("<input type=\"password\" />"), "Input secred not found") },

                Executable { assertTrue(result!!.contains("<input type=\"checkbox\" checked />"), "Checkbox not found") },
                Executable { assertTrue(result!!.contains("<input type=\"radio\" />"), "Radio button not found") },
                Executable { assertTrue(result!!.contains("table id=\"datatable\""), "Table#datatable not found") },
                Executable { assertTrue(result!!.contains("<th>city</th>"), "Column city not found") },
                Executable { assertTrue(result!!.contains("<li >Usuarios</li>"), "List item not found") }
        )
    }

    @Test
    @Throws(Exception::class)
    internal fun testStates() {
        val original = HtmlRenderer()

        val sec = Section("main", ArrayList())
        val doc = Document()
        doc.addSection(sec)
        doc.stateList.addAll(arrayListOf("enter", "error", "done"))

        val tested = StatesHtmlRenderer(original)
        tested.visit(doc)

        val result = tested.result
        assertEquals(3 + 1/*1 for style*/, result.size)

        var hasEnter = false
        var hasError = false
        for (renderResponse in result) {
            if (renderResponse.type == ResponseType.StringData) {
                if ((renderResponse.data as String).contains("div id=\"main\" class=\"error\"")) {
                    hasError = true
                } else if ((renderResponse.data as String).contains("div id=\"main\" class=\"enter\"")) {
                    hasEnter = true
                }
                println(renderResponse.data)
            }
        }

        assertTrue(hasError)
        assertTrue(hasEnter)
    }

    @Test
    @Throws(Exception::class)
    internal fun testTemplate() {
        val original = HtmlRenderer()

        // master template
        val sec = Section("main", ArrayList())
        val template = Document()
        template.addSection(sec)
        sec.children.add(Label("title"))
        sec.children.add(Button("action"))

        val second = Section("second", ArrayList())
        second.addChild(Label("replaceThis"))
        template.addSection(second)

        // child doc
        val curDoc = Document()
        val noReplace = Section("noReplace", ArrayList())
        noReplace.addChild(Label("does not appear"))
        curDoc.addSection(noReplace)

        val replaceSec = Section("second", ArrayList())
        replaceSec.addChild(Label("Clients"))
        curDoc.addSection(replaceSec)

        curDoc.template = template

        val tested = TemplateHtmlRenderer(original)
        tested.visit(curDoc)
        val result = tested.result[0].data as String?

        assertAll(
                Executable { assertFalse(result!!.contains("replaceThis"), "Replace original section content") },
                Executable { assertFalse(result!!.contains("does not appear"), "Skip section with no match id") },
                Executable { assertTrue(result!!.contains("action"), "Keeps section not overiden") },
                Executable { assertTrue(result!!.contains("Clients"), "Replace section with same ID") }
        )

        println(FormattedHtmlRenderer(tested).result)
    }

    @Test
    @Throws(Exception::class)
    internal fun testTable() {
        val render = HtmlRenderer()

        val sec = Section("main", ArrayList())
        val doc = Document()
        doc.addSection(sec)

        val tb = Table()
        tb.id = "datatable"
        sec.addChild(tb)
        tb.addChild(Column("city"))
        tb.addChild(Column("state"))
        tb.getColumnList()[0].addChild(RowData("Sao Paulo"))
        tb.getColumnList()[0].addChild(RowData("Florianopolis"))
        tb.getColumnList()[1].addChild(RowData("Sao Paulo"))
        tb.getColumnList()[1].addChild(RowData("Santa Catarina"))

        tb.getColumnList()[0].addChild(RowData("Brasilia"))

        render.visit(doc)
        val result = render.result[0].data as String?

        assertAll(
                Executable { assertTrue(result!!.contains("<td>Sao Paulo</td>")) },
                Executable { assertTrue(result!!.contains("<td>Santa Catarina</td>")) },
                Executable { assertTrue(result!!.contains("<td>Brasilia</td>")) }
        )

        println(FormattedHtmlRenderer(render).result)
    }

}
