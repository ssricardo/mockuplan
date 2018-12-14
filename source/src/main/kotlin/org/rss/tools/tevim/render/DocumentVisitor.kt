package org.rss.tools.tevim.render

import org.rss.tools.tevim.domain.Container
import org.rss.tools.tevim.domain.Document
import org.rss.tools.tevim.domain.Element
import org.rss.tools.tevim.domain.Section
import org.rss.tools.tevim.domain.element.*

/** Structures an object to run over Document itens  */
interface DocumentVisitor {

    val result: List<RenderResponse>

    fun startDocument(document: Document)

    fun finishDocument(document: Document)

    fun visit(document: Document)

    fun visit(element: Section)

    fun visit(element: Button)

    fun visit(element: Checkbox)

    fun visit(element: InputEmail)

    fun visit(element: InputSecret)

    fun visit(element: InputText)

    fun visit(element: Label)

    fun visit(element: LineHeader)

    fun visit(element: ListItem)

    fun visit(element: Radiobox)

    fun visit(element: Table)

    fun visit(element: Container<*>)

    fun visit(element: Element)
}
