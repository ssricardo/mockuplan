package org.rss.tools.mpl.builder

import org.rss.tools.mpl.domain.Container
import org.rss.tools.mpl.domain.Document
import org.rss.tools.mpl.domain.Element
import org.rss.tools.mpl.domain.Section
import org.rss.tools.mpl.domain.element.*
import org.rss.tools.mpl.parsing.grammar2.MplBaseVisitor
import org.rss.tools.mpl.parsing.grammar2.MplParser
import java.util.*

internal class MplVisitorImpl : MplBaseVisitor<Document>() {

    private var initialized = false
    private val containerQueue: Queue<Container<in Element>> = LinkedList<Container<in Element>>()
//    private val stateQueue = LinkedList<DocumentBuilder.ContainerContext>()

    companion object {
        val functionParent = mapOf(
                "document" to listOf(Root::class),
                "states" to listOf(Root::class),
                "styles" to listOf(Root::class),
                "templates" to listOf(Root::class),
                "section" to listOf(Root::class, Section::class),
                "header" to listOf(Section::class),
                "text" to listOf(Section::class),
                "cb" to listOf(Section::class),
                "check" to listOf(Section::class),
                "li" to listOf(Section::class),
                "button" to listOf(Section::class),
                "radio" to listOf(Section::class)
        )

        val nameToRealFunction = mapOf(
                "document" to MplVisitorImpl::dummy,
                "states" to MplVisitorImpl::dummy,
                "styles" to MplVisitorImpl::dummy,
                "templates" to MplVisitorImpl::dummy,
                "section" to MplVisitorImpl::section,
                "header" to MplVisitorImpl::header,
                "text" to MplVisitorImpl::text,
                "cb" to MplVisitorImpl::cb,
                "check" to MplVisitorImpl::check,
                "li" to MplVisitorImpl::li,
                "button" to MplVisitorImpl::button,
                "radio" to MplVisitorImpl::radio
        )
    }

    private fun dummy(params: List<Pair<String?, String>>): Element {
        // Do nothing
        return Label("")
    }

    private fun section (params: List<Pair<String?, String>>): Element {
        val s = Section()
        params.forEach {p ->
            when (p.first) {
                "id" -> s.id = p.second
                else -> throw IllegalArgumentException("Unrecognized parameter ${p.first}")
            }
        }
        return s
    }

    private fun header(params: List<Pair<String?, String>>): Element {
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

    private fun text(params: List<Pair<String?, String>>): Element {
        params.forEach {p ->
            return when (p.first) {
                null -> Label(p.second)
                "value" -> Label(p.second)
                else -> throw IllegalArgumentException("Unrecognized parameter ${p.first}")
            }
        }
        return Label("")
    }

    private fun cb(params: List<Pair<String?, String>>): Element = Combobox()

    private fun check (params: List<Pair<String?, String>>): Element {
        params.forEach {p ->
            return when (p.first) {
                null -> Checkbox(p.second)
                "label" -> Checkbox(p.second)
                else -> throw IllegalArgumentException("Unrecognized parameter ${p.first}")
            }
        }
        return Checkbox(null)
    }

    private fun li (params: List<Pair<String?, String>>): Element {
        params.forEach {p ->
            return when (p.first) {
                null -> ListItem(p.second)
                "text" -> ListItem(p.second)
                else -> throw IllegalArgumentException("Unrecognized parameter ${p.first}")
            }
        }
        throw IllegalStateException("<li> is missing required parameter: text")
    }

    private fun radio (params: List<Pair<String?, String>>): Element {
        params.forEach {p ->
            return when (p.first) {
                null -> Radiobox(p.second)
                "label" -> Radiobox(p.second)
                else -> throw IllegalArgumentException("Unrecognized parameter ${p.first}")
            }
        }
        return Radiobox()
    }

    private fun button (params: List<Pair<String?, String>>): Element {
        params.forEach {p ->
            return when (p.first) {
                null -> Button(p.second)
                "text" -> Button(p.second)
                else -> throw IllegalArgumentException("Unrecognized parameter ${p.first}")
            }
        }
        return Button()
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
        val paramPairList = ctx.params()?.paramsBody()?.singleParam()
                ?.map { p -> Pair(p.WORD()?.text, p.paramValue().text) }
        val element = createElement(fName, paramPairList)
        if (element != null) {
            if (containerQueue.last() === Root) {
                if (element is Section)
                    document.addSection(element)
            } else {
                containerQueue.last().addChild(element)
            }
        }
        if (element is Container<*>) {
            containerQueue.add(element as Container<in Element>)
            super.visitFunction(ctx)
            containerQueue.poll()
        } else {
            super.visitFunction(ctx)
        }
        return document
    }

    private fun createElement(fName: String?, paramList: List<Pair<String?, String>>?): Element? {
        val acceptedParents = functionParent[fName] ?:
            throw IllegalArgumentException("Function not recognized: $fName. Please check the syntax")
        val currentState = containerQueue.last()::class
        if (currentState !in acceptedParents) {
            throw IllegalStateException("""An element seems to be in an unacceptable location in the tree.
                |$fName should not be put under ${currentState.simpleName}.
            """.trimMargin())
        }
        val fn = nameToRealFunction[fName]
        return fn?.invoke(this, paramList ?: emptyList())
    }

}