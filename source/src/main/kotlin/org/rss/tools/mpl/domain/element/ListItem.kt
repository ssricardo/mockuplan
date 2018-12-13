package org.rss.tools.mpl.domain.element

import org.rss.tools.mpl.domain.Element

data class ListItem (val value: String) : Element {

    var styleClass: String? = null

    override fun toString(): String {
        return "ListItem [$value]"
    }

}
