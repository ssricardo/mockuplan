package org.rss.tools.mpl.test.language

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.rss.tools.mpl.builder.DocumentBuilder
import org.rss.tools.mpl.domain.element.Label
import org.rss.tools.mpl.domain.element.LineHeader
import org.rss.tools.mpl.domain.element.Table

class DocumentBuilderTest {

    val tested = DocumentBuilder

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

    @Test
    internal fun `test unaccepted body`() {
        assertThrows (IllegalStateException::class.java) {
            val input = """
            document {
                section(){
                    header() {
                     text()
                    }
                }
            }
        """.trimIndent().byteInputStream()
            val doc = tested.parse(input)
        }
    }

    @Test
    internal fun `test table`() {
        val input = """
            document {
                section(){
                    header("titulo")
                    table {
                        columns("value", "surname", "birth date")
                        row("Ricardo", "Testing", "1985")
                        row("Anastasia", "Russian", "1990")
                    }
                }
            }
        """.trimIndent().byteInputStream()
        val doc = tested.parse(input)
        assertEquals(2, doc.sectionList[0].children.size)
        assertTrue(doc.sectionList[0].children[1] is Table)
        assertEquals(2, (doc.sectionList[0].children[1] as Table).rowList.size)
        assertEquals(3, (doc.sectionList[0].children[1] as Table).rowList[0].columns.size)
    }
}