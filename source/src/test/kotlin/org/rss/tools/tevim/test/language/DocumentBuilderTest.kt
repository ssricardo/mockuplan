package org.rss.tools.tevim.test.language

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.function.Executable
import org.rss.tools.tevim.builder.DocumentBuilder
import org.rss.tools.tevim.domain.Document
import org.rss.tools.tevim.domain.element.*
import org.rss.tools.tevim.global.ServiceProvider
import org.rss.tools.tevim.reader.FileReader
import org.rss.tools.tevim.reader.Reader
import java.nio.file.Files
import java.nio.file.Paths

/** Also as MplVisitorImpl test */
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
        println(doc.sectionList[0].children)
        assertEquals(3, (doc.sectionList[0].children[1] as Table).rowList.size)
        assertEquals(3, (doc.sectionList[0].children[1] as Table).rowList[0].columns.size)
    }

    internal lateinit var documento: Document

    @Throws(Exception::class)
    internal fun readFile(file: String) {
        val filesPath = Paths.get(javaClass.getResource(file).path)
        val iStream = Files.newInputStream(filesPath)
        documento = tested.parse(iStream)
        assertNotNull(documento)
    }

    @Test
    @Throws(Exception::class)
    internal fun testSimpleFile() {
        readFile("/grammar-ok.mplf")
        val (_, children) = documento.sectionList[0]

        assertAll(
                Executable { assertEquals(1, documento.sectionList.size, "Contem uma seção raiz") },
                Executable { assertEquals("main", documento.sectionList[0].id, "Seção é 'main'") },

                Executable { assertEquals(LineHeader::class.java, children[0].javaClass, "Primeiro elemento é header") },
                Executable { assertEquals("meu Header", (children[0] as LineHeader).value, "Verifica texto do header") },
                Executable { assertEquals("label:", (children[1] as Label).value, "Verifica texto label") },
                Executable { assertEquals(InputText::class.java, children[2].javaClass, "Verifica input text") },
                Executable { assertEquals(Button::class.java, children[3].javaClass, "Verifica botao") },
                Executable { assertEquals(Radiobox::class.java, children[5].javaClass, "Verifica radio button") },
                Executable { assertEquals(Checkbox::class.java, children[7].javaClass, "Verifica checkbox") },
                Executable { assertEquals(InputEmail::class.java, children[9].javaClass, "Verifica email") },
                Executable { assertEquals(ListItem::class.java, children[10].javaClass, "Verifica list item") },
                Executable { assertEquals("Cidades", (children[12] as Combobox).value, "Verifica Combobox de cidades") }

                // testar combobox

        )
    }

    @Test
    @Throws(Exception::class)
    internal fun testWithTemplate() {
        ServiceProvider.register(Reader::class.java, FileReader(
                Paths.get(javaClass.getResource("/").path)))

        readFile("/grammar-template.mplf")

        assertNotNull(documento.template)
        assertNotNull(documento.template?.sectionList?.get(0)?.id == "main")
    }
}