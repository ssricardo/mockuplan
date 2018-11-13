package org.rss.tools.mpl.domain

import java.util.ArrayList

data class Document(val sectionList: MutableList<Section> = ArrayList()) {

    var template: Document? = null

    var stateList: List<String>? = null

    val styleList = ArrayList<String>()

    fun addSection(sec: Section) = this.sectionList.add(sec)

    fun addStyleFile(item: String) = styleList.add(item)

    override fun toString(): String {
        return "Document [template=$template, states=$stateList, styles=$styleList]"
    }

}