package org.rss.tools.mpl.domain

import java.util.ArrayList

data class Document(val sectionList: MutableList<Section> = ArrayList()) {

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