package org.rss.tools.tevim.test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.rss.tools.tevim.builder.*
import org.rss.tools.tevim.domain.element.*
import org.rss.tools.tevim.render.html.HtmlRenderer

/** Test the compiled code that users Kotlin style Builder */
class BuildersTest {

    @Test
    fun `test diagram`() {
        val doc = createDocument()
        assertNotNull(doc)
        assertTrue(doc.sectionList.size == 1)
        assertTrue(doc.sectionList[0].children.any { it::class == Label::class })
        assertTrue(doc.sectionList[0].children.any { it::class == Button::class })


    }

    @Test
    internal fun `test table ok`() {
        val doc = createDocument()
        val table = doc.sectionList[0].children.first { it::class == Table::class } as? Table
        assertTrue(table != null)
        assertTrue(table?.rowList?.get(0)?.columns?.size == 2)
    }

    /* Row with wrong number of elements */
    @Test
    @Disabled("Validation not enabled now")
    internal fun `test table wrong row`() {
        assertThrows(IllegalArgumentException::class.java) {
            document {
                section("sec1") {
                    table {
                        columns("value", "surname", "birth date")
                        row("Ricardo", "Testing", "1985")
                        row("John")
                    }
                }
            }
        }
    }

    private fun createDocument() = document {
//        template("test")
        states("state1", "state2", "state3")
        styles("style1", "style2", "style3")

        section("test") {
            header("Topo")
            text("Simple text")
            button()
            section("t2") {
                header("Meu teste", 2)
                cb()
                cb()
                check("Is this true?")

                li("Linha 1")
                li("Linha 2")
                li("Linha 3")

            }

            table("tab1") {
                columns("Nome", "Data de nascimento")
                row("Ricardo", "10/01/1985")
                row("Mary", "01/03/0001")
            }
        }
    }

//    @Test
    internal fun printKotlinDoc() {
        val doc = createDocument()

        val renderer = HtmlRenderer()
        renderer.visit(doc)
        println(renderer.result)
    }

    @Test
    internal fun testInput() {
        val d = document {
            section("s1") {
                t("Input text: "); iText()
                t("Email: "); iEmail()
                text("Passw: "); iSecret()
            }
        }

        assertTrue(d.sectionList[0].children.any { it.javaClass == InputText::class.java })
        assertTrue(d.sectionList[0].children.any { it.javaClass == InputEmail::class.java })
        assertTrue(d.sectionList[0].children.any { it.javaClass == InputSecret::class.java })
    }
}