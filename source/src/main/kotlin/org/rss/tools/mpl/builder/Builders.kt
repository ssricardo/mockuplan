package org.rss.tools.mpl.builder

import org.rss.tools.mpl.domain.Document
import org.rss.tools.mpl.domain.Section
import org.rss.tools.mpl.domain.element.*

/*
 * Builder functions to make possible to use a fluent suntax
 */

fun document(init: Document.() -> Unit): Document {
    val d = Document()
    d.init()
    return d
}

fun Document.section(id:String, init: Section.() -> Unit) {
    val s = Section(id)
    s.init()
    addSection(s)
}

fun Document.template(name: String) {
    this.templateRef = name
}

fun Document.states(vararg value: String) {
    value.forEach {
        this.stateList.add(it)
    }
}

fun Document.styles(vararg value: String) {
    value.forEach {
        styleList.add(it)
    }
}

fun Section.section(id:String? = null, init: Section.() -> Unit) {
    val s = Section(id)
    s.init()
    addChild(s)
}

fun Section.header(value: String, level:Int = 1) {
    val h = LineHeader(value, level)
    addChild(h)
}

fun Section.button(text: String? = null) {
    val b = Button(text)
    addChild(b)
}

fun Section.text(value: String) {
    addChild(Label(value))
}

fun Section.combobox() {
    addChild(Combobox())
}

fun Section.cb() = combobox()

fun Section.checkbox(label: String? = null) {
    addChild(Checkbox(label))
}

fun Section.check(label: String? = null) = checkbox(label)

fun Section.radio(label: String? = null) {
    addChild(Radiobox(label))
}

fun Section.li(text: String) {
    addChild(ListItem(text))
}