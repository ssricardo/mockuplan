package org.rss.tools.tevim.test

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.rss.tools.tevim.builder.DocumentBuilder
import org.rss.tools.tevim.domain.Document
import org.rss.tools.tevim.global.ServiceProvider
import org.rss.tools.tevim.parsing.Parser
import org.rss.tools.tevim.render.DocumentVisitor

import java.io.IOException
import java.io.InputStream

import org.junit.Assert.*
import org.rss.tools.tevim.Main

class ServiceProviderTest {

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
        @JvmStatic
        fun setup() {
            println("Test: Register default providers")
            Main.registerDefaultProviders()
        }
    }

}
