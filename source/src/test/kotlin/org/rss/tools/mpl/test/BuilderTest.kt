package org.rss.tools.mpl.test

import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.rss.tools.mpl.builder.*
import org.rss.tools.mpl.domain.Document
import org.rss.tools.mpl.global.ServiceProvider
import org.rss.tools.mpl.render.DocumentVisitor
import org.rss.tools.mpl.render.html.HtmlRenderer

class BuilderTest {

    @Test
    fun `test diagram`() {
        createDocument()
    }

    private fun createDocument() = document {
            section("test") {
                header("Topo")
                text("Simple text")
                button()
                section {
                    header("Meu teste", 2)
                    cb()
                    cb()
                    check("Is this true?")

                    li("Linha 1")
                    li("Linha 2")
                    li("Linha 3")

                }
            }
        }

    @Test
    @Disabled
    internal fun printKotlinDoc() {
        val doc = createDocument()

        val renderer = HtmlRenderer()
        renderer.visit(doc)
        println(renderer.result)
    }
}
