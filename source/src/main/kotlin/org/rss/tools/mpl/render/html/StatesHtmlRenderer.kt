package org.rss.tools.mpl.render.html

import org.rss.tools.mpl.domain.Document
import org.rss.tools.mpl.domain.Element
import org.rss.tools.mpl.domain.Section
import org.rss.tools.mpl.render.BaseDocumentRenderer
import org.rss.tools.mpl.render.DocumentVisitor
import org.rss.tools.mpl.render.RenderResponse
import java.util.*

/**
 * Adds support to states from Document
 * @author ricardo saturnino
 */
class StatesHtmlRenderer(private val wrapped: DocumentVisitor) : BaseDocumentRenderer(), DocumentVisitor by wrapped {

    private val responseList = ArrayList<RenderResponse>()

    private var currentState: String? = null

    override val result: List<RenderResponse>
        get() = responseList

    // ------------------------------------

    override val visitor: DocumentVisitor
        get() = this

    /**
     * For each state, calls the next DocumentVisitor and holds its result
     */
    override fun visit(document: Document) {
        if (document.stateList == null) {
            wrapped.visit(document)
            responseList.addAll(wrapped.result)
            return
        }

        for (state in document.stateList!!) {
            currentState = state
            this.startDocument(document)
            for (element in document.sectionList) {
                this.visit(element)
            }
            this.finishDocument(document)
            responseList.addAll(wrapped.result)
        }
    }

    /**
     * Add css class with the name of the current state
     */
    override fun visit(element: Section) {
        val classes = element.styleClass
        element.styleClass = if (classes == null) currentState else "$classes $currentState"
        wrapped.visit(element)
        element.styleClass = classes    // restore original ones
    }

    override fun visit(element: Element) {
      findVisitDecorated(element)
    }
}