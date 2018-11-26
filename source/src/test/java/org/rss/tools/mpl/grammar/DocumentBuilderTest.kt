package org.rss.tools.mpl.grammar

import org.junit.Assert.assertNotNull
import org.junit.jupiter.api.Test
import org.rss.tools.mpl.builder.DocumentBuilder

class DocumentBuilderTest {

    val tested = DocumentBuilder

    @Test
    internal fun mainTest() {
        val input = "document { \n function(param=\"valor\") \n }\n".byteInputStream()
        val doc = tested.parse(input)
        assertNotNull(doc)
    }
}