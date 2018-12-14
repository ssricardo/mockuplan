package org.rss.tools.tevim.builder

import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.rss.tools.tevim.domain.Container
import org.rss.tools.tevim.domain.Document
import org.rss.tools.tevim.domain.Element
import org.rss.tools.tevim.parsing.Parser
import org.rss.tools.tevim.parsing.grammar2.MplLexer
import org.rss.tools.tevim.parsing.grammar2.MplParser
import org.slf4j.LoggerFactory
import java.io.IOException
import java.io.InputStream


/**
 * Responsible for creating a [Document] from a text file input
 * @author ricardo saturnino
 */
object DocumentBuilder : Parser<Document> {

    private val LOGGER = LoggerFactory.getLogger(Parser::class.java)

    @Throws(IOException::class)
    override fun parse(input: InputStream): Document {
        LOGGER.info("Start parsing document")
        val cStream = CharStreams.fromStream(input)
        val tokens = CommonTokenStream(MplLexer(cStream))
        val parser = MplParser(tokens)

        val v = MplVisitorImpl(this)

        return v.visit(parser.document())
    }

}

object Root: Container<Element> {

    override fun children(): List<Element> {
        throw IllegalStateException("Should not call this")
    }

    override fun addChild(element: Element) {
        throw IllegalStateException("Should not call this")
    }

}