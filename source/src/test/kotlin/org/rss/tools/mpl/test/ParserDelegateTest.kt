package org.rss.tools.mpl.test

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.rss.tools.mpl.builder.DocumentBuilder
import org.rss.tools.mpl.domain.Document
import org.rss.tools.mpl.global.ServiceProvider
import org.rss.tools.mpl.parsing.Parser
import org.rss.tools.mpl.render.DocumentVisitor

import java.io.IOException
import java.io.InputStream

import org.junit.Assert.*
import org.rss.tools.mpl.Main

class ParserDelegateTest {

    @Test
    @Throws(Exception::class)
    internal fun testDefault() {
        val render = ServiceProvider[DocumentVisitor::class.java]
        val parser = ServiceProvider[Parser::class.java]

        assertNotNull(render)
        assertTrue(parser is DocumentBuilder)

        testOverrideParser()
    }

    // Needs to be executed in sequence. Otherwise it breaks the default test
    //	@Test
    @Throws(Exception::class)
    internal fun testOverrideParser() {
        ServiceProvider.register(Parser::class.java, MyParser())
        val parser = ServiceProvider[Parser::class.java]

        assertNotNull(parser)
        assertNotEquals(DocumentBuilder, parser)
    }

    internal inner class MyParser : Parser<Document> {

        @Throws(IOException::class)
        override fun parse(input: InputStream): Document? {
            return null
        }

    }

    companion object {

        @BeforeAll
        fun setup() {
            Main.registerDefaultProviders()
        }
    }

}
