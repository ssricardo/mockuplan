package org.rss.tools.mpl.render.html

import org.rss.tools.mpl.domain.Document
import org.rss.tools.mpl.domain.Element
import org.rss.tools.mpl.domain.Section
import org.rss.tools.mpl.render.BaseDocumentRenderer
import org.rss.tools.mpl.render.DocumentVisitor
import org.rss.tools.mpl.render.RenderResponse
import java.util.*

/**
 * Adds support to the use of template to Documents
 * @author ricardo saturnino
 */
class TemplateHtmlRenderer(private val wrapped: DocumentVisitor) : BaseDocumentRenderer(), DocumentVisitor by wrapped {

    private val internalSectionMap: MutableMap<String, Section> = HashMap()

    override val result: List<RenderResponse>
        get() = wrapped.result

    override val visitor: DocumentVisitor
        get() = this

    /**
     * Prepares the sections of current Document and merges it to the template Document
     */
    override fun visit(document: Document) {
        if (document.template != null) {

            // Map main sections of wrapped document
            for (section in document.sectionList) {
                internalSectionMap[section.id ?: throw IllegalStateException("Session ID null")] = section
            }

            this.startDocument(document)
            for (element in document.template!!.sectionList) {
                this.visit(element)
            }
            this.finishDocument(document)
        } else {
            wrapped.visit(document)
        }
    }

    /**
     * Prints a section or replaces the template section by the related one from child
     */
    override fun visit(element: Section) {

        // instead of print data from template, prints the one related from the wrapped
        if (internalSectionMap.containsKey(element.id)) {
            wrapped.visit(internalSectionMap[element.id] ?: throw IllegalStateException())
        } else {
            wrapped.visit(element)
        }
    }

    override fun visit(element: Element) {
        findVisitDecorated(element)
    }

}
