package org.rss.tools.tevim.domain

interface Container<E: Element>: Element {
    fun children(): List<E>

    fun addChild(element: E)
}