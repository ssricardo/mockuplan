package org.rss.tools.mpl.domain

/**
 * Containers elements. The only allowed children directly in {@link Document}
 */
data class Section(var id: String? = null,
                   val children: MutableList<Element> = arrayListOf()): Container<Element> {

    var styleClass: String? = null

    override fun children(): List<Element> = children

    override fun addChild(element: Element) {
        children.add(element)
        return
    }

}