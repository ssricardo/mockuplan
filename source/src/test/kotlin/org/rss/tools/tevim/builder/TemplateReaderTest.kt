package org.rss.tools.tevim.builder

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.rss.tools.tevim.domain.Document
import org.rss.tools.tevim.domain.element.LineHeader
import org.rss.tools.tevim.global.ServiceProvider
import org.rss.tools.tevim.reader.FileReader
import org.rss.tools.tevim.reader.Reader
import org.rss.tools.tevim.reader.TemplateProvider
import java.lang.IllegalArgumentException
import java.nio.file.Paths

internal class TemplateReaderTest {

    val tested: TemplateReader

    init {
        ServiceProvider.register(Reader::class.java, FileReader(
                Paths.get(javaClass.getResource("/").path)))
        tested = TemplateReader
    }

    val mainLayout = document {
        section("main") {
            header("Hello Ricardo")
        }
        section("footer") {
            t("Produced by Anakin Skywalker")
        }
    }

    @Test
    internal fun `load ok`() {
        val provider = object: TemplateProvider {
            override fun provideTemplates(): Map<String, Document> {
                return mapOf("mainLayout" to mainLayout)
            }

        }
        val d = document {
            template("mainLayout")
        }
        tested.process(d, provider)

        assertNotNull(d.template)
        assertTrue(d.template?.sectionList?.size == 2)
        assertTrue(d.template!!.sectionList[0].children[0] is LineHeader)
    }

    @Test
    internal fun `document not exists`() {
        assertThrows(IllegalArgumentException::class.java) {
            val provider = object: TemplateProvider {
                override fun provideTemplates(): Map<String, Document> {
                    return mapOf("mainLayout" to mainLayout)
                }

            }
            val d = document {
                template("notNoNo")
            }
            tested.process(d, provider)
        }

    }

    @Test
    internal fun `read from file ok`() {
        val parser = DocumentBuilder
        val d = document {}
        tested.process(parser, d, "grammar-template.mplf")
        assertNotNull(d.template)
    }
}