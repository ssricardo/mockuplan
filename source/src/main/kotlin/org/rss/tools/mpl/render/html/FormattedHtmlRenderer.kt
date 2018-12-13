package org.rss.tools.mpl.render.html

import org.rss.tools.mpl.domain.Container
import org.rss.tools.mpl.domain.Document
import org.rss.tools.mpl.domain.Element
import org.rss.tools.mpl.domain.Section
import org.rss.tools.mpl.domain.element.*
import org.rss.tools.mpl.render.DocumentVisitor
import org.rss.tools.mpl.render.RenderResponse
import org.rss.tools.mpl.render.ResponseType
import org.w3c.tidy.Tidy

import java.io.StringReader
import java.io.StringWriter

/**
 * Used to format the output created by other visitors.
 * @author ricardo
 */
class FormattedHtmlRenderer(private val wrapped: DocumentVisitor) : DocumentVisitor {

    override//				System.out.println("Ignoring " + item.getData());
    val result: List<RenderResponse>
        get() {
            val tidy = Tidy()
            tidy.indentContent = true
            tidy.showWarnings = false

            val writer = StringWriter()

            for (item in wrapped.result) {
                if (item.type == ResponseType.StringData) {
                    tidy.parse(StringReader(item.data as String), writer)
                    item.data = writer.toString()
                } else {
                }
            }

            return wrapped.result
        }

    override fun startDocument(document: Document) {
        wrapped.startDocument(document)
    }

    override fun finishDocument(document: Document) {
        wrapped.finishDocument(document)
    }

    override fun visit(document: Document) {
        wrapped.visit(document)
    }

    override fun visit(element: Section) {
        wrapped.visit(element)
    }

    override fun visit(element: Button) {
        wrapped.visit(element)
    }

    override fun visit(element: Checkbox) {
        wrapped.visit(element)
    }

    override fun visit(element: InputEmail) {
        wrapped.visit(element)
    }

    override fun visit(element: InputSecret) {
        wrapped.visit(element)
    }

    override fun visit(element: InputText) {
        wrapped.visit(element)
    }

    override fun visit(element: Label) {
        wrapped.visit(element)
    }

    override fun visit(element: LineHeader) {
        wrapped.visit(element)
    }

    override fun visit(element: ListItem) {
        wrapped.visit(element)
    }

    override fun visit(element: Radiobox) {
        wrapped.visit(element)
    }

    override fun visit(element: Table) {
        wrapped.visit(element)
    }

    override fun visit(element: Container<*>) {
        wrapped.visit(element)
    }

    override fun visit(element: Element) {
        wrapped.visit(element)
    }
}
