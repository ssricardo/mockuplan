package org.rss.tools.tevim.builder

import org.rss.tools.tevim.domain.Document
import org.rss.tools.tevim.domain.Section
import org.rss.tools.tevim.domain.element.*

/*
 * Builder functions to make possible to use a fluent syntax
 */

/** Root element. Has no Attributes, all other elements must be within this one */
fun document(init: Document.() -> Unit): Document {
    val d = Document()
    d.init()
    return d
}

/**
 * @see Section
 * @param id Identification
 * @param styleClass css class attribute
 */
fun Document.section(id:String, styleClass: String? = null, init: Section.() -> Unit) {
    val s = Section(id)
    styleClass?.let { s.styleClass = it }
    s.init()
    addSection(s)
}

/**
 * @param name The value (reference) of the base document for this one
 */
fun Document.template(name: String) {
    this.templateRef = name
}

/**
 * Names (label) representing different states of the app
 */
fun Document.states(vararg value: String) {
    value.forEach {
        this.stateList.add(it)
    }
}

/**
 * Referenced stylesheets (css) for this document
 */
fun Document.styles(vararg value: String) {
    value.forEach {
        styleList.add(it)
    }
}

/**
 * Add another Section as a child of this one
 */
fun Section.section(id:String, styleClass: String? = null,  init: Section.() -> Unit) {
    val s = Section(id)
    styleClass?.let { style -> s.styleClass = style }
    s.init()
    addChild(s)
}

/**
 * @see LineHeader
 */
fun Section.header(value: String, level:Int = 1) {
    val h = LineHeader(value, level)
    addChild(h)
}

/**
 * @see Button
 */
fun Section.button(text: String? = null) {
    val b = Button(text)
    addChild(b)
}

/**
 * @see Label
 */
fun Section.text(value: String) {
    addChild(Label(value))
}

fun Section.t(txt: String) = text(txt)

/**
 * @see Combobox
 */
fun Section.combobox() {
    addChild(Combobox())
}

/**
 * @see Combobox
 */
fun Section.cb() = combobox()

/**
 * @see Checkbox
 */
fun Section.checkbox(label: String? = null) {
    addChild(Checkbox(label))
}

/**
 * @see Checkbox
 */
fun Section.check(label: String? = null) = checkbox(label)

/**
 * @see Radiobox
 */
fun Section.radio(label: String? = null) {
    addChild(Radiobox(label))
}

/**
 * @see ListItem
 */
fun Section.li(text: String) {
    addChild(ListItem(text))
}

// ---------------------------------------- Table ------------------------------------

/**
 * @see Table
 */
fun Section.table(id:String? = null, body: Table.() -> Unit) {
    val tab = Table(id)
    tab.body()
    addChild(tab)
}

/**
 * Specify the columns (its headers) for this table
 */
fun Table.columns(vararg names: String) {
    names.forEach {
        addChild(Column(it))
    }
}

/**
 * Specify values for a row in this table.
 * The number of strings must match the number of declared columns
 */
fun Table.row (vararg values: String) {
    /*
    if (values.size != columnList.size) {
        throw IllegalArgumentException("The number of entries in a row must be the same as ")
    }*/
    addChild(RowData(*values))
}

// ---------------------------------------- Input ------------------------------------

fun Section.inputText() {
    addChild(InputText())
}

fun Section.iText() = inputText()

fun Section.inputEmail() = addChild(InputEmail())

fun Section.iEmail() = inputEmail()

fun Section.inputSecret() = addChild(InputSecret())

fun Section.iSecret() = inputSecret()