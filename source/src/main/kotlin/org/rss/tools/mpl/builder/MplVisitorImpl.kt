package org.rss.tools.mpl.builder

import org.rss.tools.mpl.domain.Container
import org.rss.tools.mpl.domain.Document
import org.rss.tools.mpl.domain.Element
import org.rss.tools.mpl.domain.Section
import org.rss.tools.mpl.domain.element.*
import org.rss.tools.mpl.parsing.grammar2.MplBaseVisitor
import org.rss.tools.mpl.parsing.grammar2.MplParser
import java.util.*
import kotlin.reflect.KClass

internal class MplVisitorImpl : MplBaseVisitor<Document>() {

    private var initialized = false
    private val containerQueue: Queue<Container<in Element>> = LinkedList<Container<in Element>>()
//    private val stateQueue = LinkedList<DocumentBuilder.ContainerContext>()

    data class FunctionImpl (val fn: (List<Pair<String?, String>>) -> Element,
                             val acceptedParent: List<KClass<out Container<Element>>>,
                             val acceptBody:Boolean = false)

    companion object {
        val functionMap = mapOf(
//                "document" to FunctionImpl(::visitDummy, listOf(Root::class), true),
                "section" to FunctionImpl(::visitSection, listOf(Root::class, Section::class), true),
                "header" to FunctionImpl(::visitHeader, listOf(Section::class)),
                "text" to FunctionImpl(::visitText, listOf(Section::class)),
                "t" to FunctionImpl(::visitText, listOf(Section::class)),
                "cb" to FunctionImpl(::visitCb, listOf(Section::class)),
                "combobox" to FunctionImpl(::visitCb, listOf(Section::class)),
                "checkbox" to FunctionImpl(::visitCheck, listOf(Section::class)),
                "check" to FunctionImpl(::visitCheck, listOf(Section::class)),
                "li" to FunctionImpl(::visitLi, listOf(Section::class)),
                "radio" to FunctionImpl(::visitRadio, listOf(Section::class)),
                "button" to FunctionImpl(::visitButton, listOf(Section::class)),

                "inputtext" to FunctionImpl(::visitInputText, listOf(Section::class)),
                "itext" to FunctionImpl(::visitInputText, listOf(Section::class)),
                "inputemail" to FunctionImpl(::visitInputEmail, listOf(Section::class)),
                "iemail" to FunctionImpl(::visitInputEmail, listOf(Section::class)),
                "inputsecret" to FunctionImpl(::visitInputSecret, listOf(Section::class)),
                "isecret" to FunctionImpl(::visitInputSecret, listOf(Section::class)),

                "table" to FunctionImpl(::visitTable, listOf(Section::class), true),
                "columns" to FunctionImpl(::visitColumns, listOf(Table::class)),
                "row" to FunctionImpl(::visitRow, listOf(Table::class))
        )

        val specialFunctionList = listOf("states", "styles", "template", "document")
    }

    val document = Document()

    override fun visitDocument(ctx: MplParser.DocumentContext): Document {
        initialized = true
        containerQueue.add(Root)
        super.visitDocument(ctx)
        return document
    }

    override fun visitFunction(ctx: MplParser.FunctionContext): Document {
        require(initialized)
        val fName = ctx.name().WORD().text
        if (fName in specialFunctionList) {
            handleSpecial(fName, ctx)
            return document
        }

        val paramPairList = getParamPairsFromCtx(ctx)
        val funMap = findFunctionImpl(fName)
        val element = funMap.fn.invoke(paramPairList)
        addElementToParent(element, funMap)
        visitChildrenFun(element, funMap, ctx)
        return document
    }


    private fun visitChildrenFun(element: Element, funMap: FunctionImpl, ctx: MplParser.FunctionContext) {
        if (ctx.body() != null && ! funMap.acceptBody) {
            throw IllegalStateException("Trying to use a body in function that does not accept it: $element")
        }
        if (element is Container<*>) {
            containerQueue += element as Container<in Element>
            super.visitFunction(ctx)
            containerQueue.poll()
        } else {
            super.visitFunction(ctx)
        }
    }

    private fun addElementToParent(element: Element, funMap: FunctionImpl) {
        val currentState = containerQueue.last()
        if (currentState::class !in funMap.acceptedParent) {
            throw IllegalStateException("""An element seems to be in an unacceptable location in the tree.
                |$element should not be put under ${currentState::class.simpleName}.
            """.trimMargin())
        }

        if (currentState === Root) {
            if (element is Section)
                document.addSection(element)
        } else {
            if (element is MultipleWrapper<*>) {
                val list = element.value
                list.forEach (currentState::addChild)
            } else {
                currentState.addChild(element)
            }
        }
    }

    private fun findFunctionImpl(fName: String?): FunctionImpl = functionMap[fName]
            ?: throw IllegalArgumentException("Function not recognized: $fName. Please check the syntax")

    private fun getParamPairsFromCtx(ctx: MplParser.FunctionContext): List<Pair<String?, String>> {
        return ctx.params()?.paramsBody()?.singleParam()
                ?.map { p ->
                    Pair(p.WORD()?.text, p.paramValue().text.removePrefix("\"").removeSuffix("\"")) }
                ?: emptyList()
    }


    /** Document level functions. Distinct handle regarding other Elements  */
    private fun handleSpecial(fName: String, ctx: MplParser.FunctionContext) {
        val params = getParamPairsFromCtx(ctx)
        when(fName) {
            "document" -> super.visitFunction(ctx)
            "state" -> params.map { it.second }.forEach { document.states(it) }
            "styles" -> params.map { it.second }.forEach { document.addStyleFile(it) }
        }
    }


/*
    private fun createElement(fName: String?, paramList: List<Pair<String?, String>>?): Element? {
        val fnImpl = functionMap[fName]
        val acceptedParents = fnImpl?.acceptedParent ?:
            throw IllegalArgumentException("Function not recognized: $fName. Please check the syntax")
        val currentState = containerQueue.last()::class
        if (currentState !in acceptedParents) {
            throw IllegalStateException("""An element seems to be in an unacceptable location in the tree.
                |$fName should not be put under ${currentState.simpleName}.
            """.trimMargin())
        }
        return fnImpl.fn.invoke(paramList ?: emptyList())
    }*/

}

// --------------------- Real Functions for each string function value-- -----------------

private fun visitSection (params: List<Pair<String?, String>>): Element {
    val s = Section()
    params.forEach {p ->
        when (p.first) {
            "id" -> s.id = p.second
            null -> s.id = p.second
            "styleClass" -> s.styleClass = p.second
            else -> throw IllegalArgumentException("Unrecognized parameter ${p.first}")
        }
    }
    return s
}

private fun visitHeader(params: List<Pair<String?, String>>): Element {
    var value = ""
    var level = 1
    params.forEach {p ->
        when (p.first) {
            null -> value = p.second
            "value" -> value = p.second
            "level" -> level = p.second.toInt()
            else -> throw IllegalArgumentException("Unrecognized parameter ${p.first}")
        }
    }
    return LineHeader(value, level)
}

private fun visitText(params: List<Pair<String?, String>>): Element {
    params.forEach {p ->
        return when (p.first) {
            null -> Label(p.second)
            "value" -> Label(p.second)
            else -> throw IllegalArgumentException("Unrecognized parameter ${p.first}")
        }
    }
    return Label("")
}

private fun visitCb(params: List<Pair<String?, String>>): Element {
    params.forEach {p ->
        return when (p.first) {
            null -> Combobox(p.second)
            "value" -> Combobox(p.second)
            else -> throw IllegalArgumentException("Unrecognized parameter ${p.first}")
        }
    }
    return Combobox()
}

private fun visitCheck (params: List<Pair<String?, String>>): Element {
    params.forEach {p ->
        return when (p.first) {
            null -> Checkbox(p.second)
            "label" -> Checkbox(p.second)
            else -> throw IllegalArgumentException("Unrecognized parameter ${p.first}")
        }
    }
    return Checkbox(null)
}

private fun visitLi (params: List<Pair<String?, String>>): Element {
    params.forEach {p ->
        return when (p.first) {
            null -> ListItem(p.second)
            "_text" -> ListItem(p.second)
            else -> throw IllegalArgumentException("Unrecognized parameter ${p.first}")
        }
    }
    throw IllegalStateException("<li> is missing required parameter: _text")
}

private fun visitRadio (params: List<Pair<String?, String>>): Element {
    params.forEach {p ->
        return when (p.first) {
            null -> Radiobox(p.second)
            "label" -> Radiobox(p.second)
            else -> throw IllegalArgumentException("Unrecognized parameter ${p.first}")
        }
    }
    return Radiobox()
}

private fun visitButton (params: List<Pair<String?, String>>): Element {
    params.forEach {p ->
        return when (p.first) {
            null -> Button(p.second)
            "text" -> Button(p.second)
            else -> throw IllegalArgumentException("Unrecognized parameter ${p.first}")
        }
    }
    return Button()
}

private fun visitTable(params: List<Pair<String?, String>>): Element {
    params.forEach {p ->
        return when (p.first) {
            null -> Table(p.second)
            "id" -> Table(p.second)
            else -> throw IllegalArgumentException("Unrecognized parameter ${p.first}")
        }
    }
    return Table()
}

private fun visitColumns(params: List<Pair<String?, String>>): Element =
        visitTableElement(params, Column::class) { Column(it) }

private fun visitRow(params: List<Pair<String?, String>>): Element =
        visitTableElement(params, RowData::class) { RowData(it) }

private inline fun <T:Element> visitTableElement(params: List<Pair<String?, String>>,
                                                 klass: KClass<T>, producer: (String)-> T): Element {
    val cList = mutableListOf<T>()
    params.forEach {p ->
        when (p.first) {
            null -> cList.add(producer(p.second))
            "names" -> cList.add(producer(p.second))
            else -> throw IllegalArgumentException("Unrecognized parameter ${p.first}")
        }
    }
    return if (cList.isNotEmpty())
        MultipleWrapper(cList)
    else throw IllegalArgumentException("No ${klass.simpleName} was specified. ${klass.simpleName} expects at least one item")
}

private fun visitInputText(params: List<Pair<String?, String>>): Element {
    if (params.isNotEmpty()) {
        throw IllegalArgumentException("Unrecognized parameter ${params}")
    }
    return InputText()
}

private fun visitInputEmail(params: List<Pair<String?, String>>): Element {
    if (params.isNotEmpty()) {
        throw IllegalArgumentException("Unrecognized parameter ${params}")
    }
    return InputEmail()
}

private fun visitInputSecret(params: List<Pair<String?, String>>): Element {
    if (params.isNotEmpty()) {
        throw IllegalArgumentException("Unrecognized parameters ${params}")
    }
    return InputSecret()
}


/**
 * Used to group multiple elements into one, to obey the expected function signature
 */
class MultipleWrapper<E:Element>(val value: List<E>): Element