package org.rss.tools.tevim.domain.element

import java.util.ArrayList

import org.rss.tools.tevim.domain.Container
import org.rss.tools.tevim.domain.Element

/**
 Represents a list for tabular data
 */
data class Table (var id: String? = null): Container<Element> {

//    val columnList = ArrayList<Column>()

    val rowList: MutableList<RowData> by lazy {
        ArrayList<RowData>()
    }

    override fun toString(): String = "Table [$id, data=$rowList]"

    override fun children(): List<Element> {
        return rowList
    }

    override fun addChild(element: Element) {
        when (element) {
            is RowData -> rowList.add(element)

            is Column -> {
                // always first row
                if (rowList.size == 0) {
                    rowList.add(RowData())
                }
                rowList[0].addChild(element)
            }
            else ->
                throw IllegalArgumentException("Table Accepts only columns and rows. Trying with ${element::class}")
        }
    }

}

/** Holds some sample data to fill elements in a View  */
class RowData : Element, Container<Column> {

    val columns = mutableListOf<Column>()

    constructor(vararg valueList:String) : super() {
        valueList.map (::Column).forEach { columns.add(it) }
    }

    constructor(value: Column) {
        columns.add(value)
    }

    override fun children(): List<Column> = columns

    override fun addChild(element: Column) {
        columns.add(element)
    }

    override fun toString(): String  = "RowData [${columns.size} columns]"

}


/** Column of a table  */
data class Column (val value:String): Element