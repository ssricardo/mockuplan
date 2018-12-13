package org.rss.tools.mpl.domain.element

import org.rss.tools.mpl.domain.Element
//import kotlin.reflect.KProperty

/*class LevelDelegate {

    private var level: Int = 1

    *//**
      Prevents values out of range
     *//*
    operator fun setValue(thisRef: LineHeader, property: KProperty<*>, value: Int){
        level = Math.max(value, 1)
        level = Math.min(level, 6)
    }

    operator fun getValue(thisRef: LineHeader, property: KProperty<*>): Int = level
}*/

data class LineHeader (var value: String, private val _level:Int = 1) : Element {

    var level = _level
    set(value) {
        field = Math.max(value, 1)
        field = Math.min(field, 6)
    }

    override fun toString(): String {
        return "LineHeader [$level, $value]"
    }

}

