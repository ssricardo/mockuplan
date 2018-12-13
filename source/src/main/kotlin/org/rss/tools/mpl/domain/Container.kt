package org.rss.tools.mpl.domain

interface Container<E: Element>: Element {
    fun children(): List<E>

    fun addChild(element: E)
}