package org.rss.tools.tevim.domain

import java.util.ArrayList

/**
 * Represents the root element.
 * There must be only one of this per file
 */
data class Document(val sectionList: MutableList<Section> = ArrayList()) {

    /** Refers to the base document for this */
    var template: Document? = null
    var templateRef: String? = null

    val stateList = mutableListOf<String>()

    val styleList = mutableListOf<String>()

    fun addSection(sec: Section) = this.sectionList.add(sec)

    fun addStyleFile(item: String) = styleList.add(item)

    override fun toString(): String {
        return "Document [template=$template, states=$stateList, styles=$styleList]"
    }

}