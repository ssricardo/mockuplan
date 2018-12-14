package org.rss.tools.tevim.domain.element

import org.rss.tools.tevim.domain.Element

class Checkbox (var label: String?) : Element

class Combobox (var value: String? = null) : Element {

    override fun toString(): String {
        return "Combobox [$value]"
    }
}

class Radiobox (var label: String? = null) : Element
