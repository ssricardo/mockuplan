package org.rss.tools.mpl.render.html

import org.rss.tools.mpl.domain.Container
import org.rss.tools.mpl.domain.Document
import org.rss.tools.mpl.domain.Element
import org.rss.tools.mpl.domain.Section
import org.rss.tools.mpl.domain.element.*
import org.rss.tools.mpl.render.BaseDocumentRenderer
import org.rss.tools.mpl.render.DocumentVisitor
import org.rss.tools.mpl.render.RenderResponse
import org.rss.tools.mpl.render.ResponseType
import org.rss.tools.mpl.validation.AppException
import org.rss.tools.mpl.validation.AppValidation
import java.util.*

/**
 * Produces an HTML String from Document objects
 * @author ricardo saturnino
 */
class HtmlRenderer : BaseDocumentRenderer(), DocumentVisitor {

    private var sbBody = StringBuilder()

    private var html = StringBuilder()

    private val response = ArrayList<RenderResponse>()

    override val result: List<RenderResponse>
        get() {
            response[0].data = html.toString()
            response[0].type = ResponseType.StringData
            return response
        }

    override val visitor: DocumentVisitor
        get() = this

    override fun visit(document: Document) {
        startDocument(document)

        for (element in document.sectionList) {
            visit(element)
        }

        finishDocument(document)
    }

    override fun startDocument(document: Document) {
        html = StringBuilder()
        sbBody = StringBuilder()
        response.clear()
        response.add(RenderResponse(ResponseType.StringData, null)) // response 0 is always the html

        val stylesPart = getStyles(document.styleList)

        html.append("<html><head>")
                .append(stylesPart)
                .append("</head>")
                .append(System.lineSeparator())
                .append("<body>")
    }

    override fun finishDocument(document: Document) {
        html.append(sbBody.toString())
                .append("</body>")
                .append(System.lineSeparator())

        html.append("</html>")
    }

    private fun getStyles(styleList: MutableList<String>): String {
        if (styleList.isEmpty()) {
            styleList.add(PredefinedStyle.DEFAULT.toString())
        }
        val itStyles = styleList.listIterator()
        while (itStyles.hasNext()) {
            val ref = itStyles.next()

            for (enumItem in PredefinedStyle.values()) {    // higher or lower cost than exception in valueOf() ?
                if (enumItem.toString().toLowerCase() == ref.trim { it <= ' ' }.toLowerCase()) {
                    itStyles.set(enumItem.fileName)
                    response.add(RenderResponse(ResponseType.PredefinedStyle, enumItem))
                }
            }
        }

        val sb = StringBuilder()
        for (item in styleList) {
            sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"")
                    .append(item)
                    .append("\" />")
        }
        return sb.toString()
    }

    // ---------------------------- Visitings -------------------------

    override fun visit(element: Section) {
        sbBody.append("<div ")
                .append("id=\"")
                .append(element.id)
                .append("\" ")

        printStyleClass(element.styleClass)
        sbBody.append(">")

        for (child in element.children) {
            visit(child)
        }
        sbBody.append("</div>")
    }

    override fun visit(element: Button) {
        sbBody.append("<button type=\"button\">")
                .append(element.value)
                .append("</button>")
    }

    override fun visit(element: Checkbox) {
        sbBody.append("<input type=\"checkbox\" checked />")
    }

    override fun visit(element: InputText) {
        sbBody.append("<input type=\"text\" />")
    }

    override fun visit(element: InputEmail) {
        sbBody.append("<input type=\"email\" />")
    }

    override fun visit(element: InputSecret) {
        sbBody.append("<input type=\"password\" />")
    }

    override fun visit(element: Label) {
        sbBody.append("<label>")
                .append(element.value)
                .append("</label>")
    }

    override fun visit(element: LineHeader) {
        sbBody
                .append("<h")
                .append(element.level)
                .append(">")
                .append(element.value)
                .append("</h")
                .append(element.level)
                .append(">")
                .append(System.lineSeparator())
    }

    // TODO open/close the UL
    override fun visit(element: ListItem) {
        sbBody.append("<li ")
        printStyleClass(element.styleClass)
        sbBody
                .append(">")
                .append(element.value)
                .append("</li>")
                .append(System.lineSeparator())
    }

    override fun visit(element: Radiobox) {
        sbBody.append("<input type=\"radio\" />")
    }

    override fun visit(element: Table) {
        if (element.id == null) {
            throw AppException(AppValidation.TABLE_NO_ID)
        }

        sbBody.append("<table id=\"{id}\" border=\"0\"> ".replace("{id}", element.id!!))
        sbBody.append("<thead>")

        element.rowList.firstOrNull()?.let {row ->
            row.columns.forEach {c ->
                sbBody.append("<th>${c.value}</th>")
            }
        }

        sbBody.append("</thead>")

        // print rows
        element.rowList.forEachIndexed { index, row ->
            if (index > 0) {
                sbBody.append("<tr>")
                row.columns.forEach {c ->
                    sbBody.append("<td>${c.value}</td>")
                }
                sbBody.append("</tr>")
                        .append(System.lineSeparator())
            }
        }

        sbBody.append("</table>")
                .append(System.lineSeparator())
    }

    override fun visit(element: Container<*>) {}

    /** Finds the correct decorator by the runtime instance class e redirects to the correct visit()  */
    override fun visit(element: Element) {
        findVisitDecorated(element)
    }

    private fun printStyleClass(value: String?) {
        if (value != null) {
            sbBody.append("class=\"")
                    .append(value)
                    .append("\"")
        }
    }

}