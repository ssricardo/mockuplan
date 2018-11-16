package org.rss.tools.mpl.domain.element

import java.util.ArrayList

import org.rss.tools.mpl.domain.Container
import org.rss.tools.mpl.domain.Element

data class Table (var id: String? = null): Container<Column> {

    private val columnList = ArrayList<Column>()

    fun getColumnList(): List<Column> {
        return columnList
    }

    override fun toString(): String = "Table [$id, columns=$columnList]"

    override fun children(): List<Column> {
        return getColumnList()
    }

    override fun addChild(element: Column) {
        columnList.add(element)
    }

}

/** Holds some sample data to fill elements in a View  */
class RowData : Element {

    var data: Any? = null

    constructor() : super()

    constructor(data: Any) : super() {
        this.data = data
    }

    override fun toString(): String  = "RowData [$data]"

}


/** Column of a table  */
data class Column (val name:String): Container<RowData>, Element {

    private var rowList: MutableList<RowData>? = null

    fun getRowList(): List<RowData>? {
        return rowList
    }

    fun setRowList(rowList: MutableList<RowData>) {
        this.rowList = rowList
    }

    override fun children(): List<RowData> {
        return getRowList() ?: emptyList()
    }

    override fun addChild(element: RowData) {
        if (rowList == null) {
            rowList = ArrayList()
        }
        this.rowList!!.add(element)
    }

}