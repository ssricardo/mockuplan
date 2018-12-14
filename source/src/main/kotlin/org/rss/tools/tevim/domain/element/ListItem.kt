package org.rss.tools.tevim.domain.element

import org.rss.tools.tevim.domain.Element

data class ListItem (val value: String) : Element {

    var styleClass: String? = null

    override fun toString(): String {
        return "ListItem [$value]"
    }

}
