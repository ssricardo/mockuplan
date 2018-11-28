package org.rss.tools.mpl.test

import org.junit.Assert.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.rss.tools.mpl.builder.DocumentBuilder
import org.rss.tools.mpl.domain.element.Label
import org.rss.tools.mpl.domain.element.LineHeader

class DocumentBuilderTest {

    val tested = DocumentBuilder

    @Test
    @Disabled
    internal fun mainTest() {
        val input = "document { \n function(param=\"valor\") \n }\n".byteInputStream()
        val doc = tested.parse(input)
        assertNotNull(doc)
    }

    @Test
    internal fun basicFunctions() {
        val input = """
            document {
                section(){
                    header()
                    text()
                    check()

                    button()
                }
            }
        """.trimIndent().byteInputStream()
        val doc = tested.parse(input)
        assertEquals(1, doc.sectionList.size)
        assertEquals(4, doc.sectionList[0].children.size)
        assertTrue(doc.sectionList[0].children[0] is LineHeader)
        assertTrue(doc.sectionList[0].children[1] is Label)
    }

    @Test
    internal fun acceptedParameters() {
        val input = """
            document {
                section(){
                    header("titulo", level=1)
                    text("labelll")
                    check("cb1")

                    li("Item1")
                    button("Action")
                }
            }
        """.trimIndent().byteInputStream()
        val doc = tested.parse(input)
        assertNotNull(doc)
    }
}