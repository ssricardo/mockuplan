package org.rss.tools.mpl.test.language

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.rss.tools.mpl.builder.DocumentBuilder
import org.rss.tools.mpl.domain.Document
import org.rss.tools.mpl.domain.Element
import org.rss.tools.mpl.domain.Section
import org.rss.tools.mpl.domain.element.*
import org.rss.tools.mpl.global.ServiceProvider
import org.rss.tools.mpl.parsing.Parser
import org.rss.tools.mpl.reader.FileReader
import org.rss.tools.mpl.reader.Reader

import java.nio.file.Files
import java.nio.file.Paths

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.function.Executable

/* Old parser test - to be updated */
class MplParserTest {

    internal var parser: Parser<Document> = DocumentBuilder
    internal lateinit var documento: Document

    @Throws(Exception::class)
    internal fun readFile(file: String) {
        val filesPath = Paths.get(MplParserTest::class.java.getResource(file).path)
        val iStream = Files.newInputStream(filesPath)
        documento = parser.parse(iStream)
        assertNotNull(documento)
    }

    @Test
    @Throws(Exception::class)
    internal fun testSimpleFile() {
        readFile("/grammar-ok.mplf")
        val (_, children) = documento.sectionList[0]

        println(children)

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

    /*
    @Test
    @Throws(Exception::class)
    internal fun testTables() {
        readFile("/grammar-table.mplf")
        val (_, children) = documento.sectionList[0]

        assertEquals(Table::class.java, children[1].javaClass)
        val firstTable = children[1] as Table

        assertAll(
                Executable { assertEquals(2, firstTable.columnList.size, "First table should have 2 columns") },
                Executable { assertEquals("cidade", firstTable.columnList[1].value, "Column value = cidade") }
        )

        assertEquals(Table::class.java, (children[1] as Table).javaClass)
        val secTable = (children[2] as Section).children[0] as Table

        assertAll(
                Executable { assertEquals(2, secTable.columnList.size, "Second table have 2 columns") },
                Executable { assertEquals("idade", secTable.columnList[1].value, "Column value = idade") },
                Executable { assertNotNull(secTable.columnList[0].rowList, "Row list not null in a column") },
                Executable { assertEquals("ricardo", secTable.columnList[0].rowList[0].data, "Check Row data value") }
        )
    }
    */

    @Test
    @Throws(Exception::class)
    internal fun testWithTemplate() {
        readFile("/grammar-template.mplf")
        val (id) = documento.sectionList[0]

        assertAll(
                Executable { assertEquals("body", id) },
                Executable { assertNotNull(documento.template) },
                Executable { assertEquals("main", documento.template!!.sectionList[0].id) }
        )

    }

    companion object {

        @BeforeAll
        internal fun prepare() {
            ServiceProvider.register(Reader::class.java, FileReader(
                    Paths.get(MplParserTest::class.java.getResource("/").path)))
        }
    }
}
