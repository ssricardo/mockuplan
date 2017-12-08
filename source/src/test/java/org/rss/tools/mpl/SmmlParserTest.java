package org.rss.tools.mpl;

import static org.junit.jupiter.api.Assertions.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.rss.tools.mpl.domain.Document;
import org.rss.tools.mpl.domain.Element;
import org.rss.tools.mpl.domain.Section;
import org.rss.tools.mpl.domain.element.Button;
import org.rss.tools.mpl.domain.element.Checkbox;
import org.rss.tools.mpl.domain.element.Combobox;
import org.rss.tools.mpl.domain.element.InputEmail;
import org.rss.tools.mpl.domain.element.InputText;
import org.rss.tools.mpl.domain.element.Label;
import org.rss.tools.mpl.domain.element.LineHeader;
import org.rss.tools.mpl.domain.element.ListItem;
import org.rss.tools.mpl.domain.element.Radiobox;
import org.rss.tools.mpl.domain.element.Table;
import org.rss.tools.mpl.global.ServiceProvider;
import org.rss.tools.mpl.parsing.MplDocumentParser;
import org.rss.tools.mpl.reader.FileReader;
import org.rss.tools.mpl.reader.Reader;

public class SmmlParserTest {

	MplDocumentParser parser = MplDocumentParser.getInstance();
	Document documento;
	
	@BeforeAll
	static void prepare () {
		ServiceProvider.register(Reader.class, new FileReader(
				Paths.get(SmmlParserTest.class.getResource("/").getPath())));
	}

	void readFile(String file) throws Exception {
		Path filesPath = Paths.get(SmmlParserTest.class.getResource(file).getPath());
		InputStream is = Files.newInputStream(filesPath);
		documento = parser.parse(is);
		assertNotNull(documento);
	}

	@Test
	void testSimpleFile() throws Exception {
		readFile("/grammar-ok.mplf");
		Section section = documento.getSectionList().get(0);
		
		System.out.println(section.getChildren());

		assertAll(
				() -> assertEquals(1, documento.getSectionList().size(), "Contem uma seção raiz"),
				() -> assertEquals("main", documento.getSectionList().get(0).getId(), "Seção é 'main'"),

				() -> assertEquals(LineHeader.class, section.getChildren().get(0).getClass(), "Primeiro elemento é header"),
				() -> assertEquals("meu Header", ((LineHeader) section.getChildren().get(0)).getValue(), "Verifica texto do header"),
				() -> assertEquals("label:", ((Label) section.getChildren().get(1)).getValue(), "Verifica texto label"),
				() -> assertEquals(InputText.class, section.getChildren().get(2).getClass(), "Verifica input text"),
				() -> assertEquals(Button.class, section.getChildren().get(3).getClass(), "Verifica botao"),
				() -> assertEquals(Radiobox.class, section.getChildren().get(5).getClass(), "Verifica radio button"),
				() -> assertEquals(Checkbox.class, section.getChildren().get(7).getClass(), "Verifica checkbox"),
				() -> assertEquals(InputEmail.class, section.getChildren().get(9).getClass(), "Verifica email"),
				() -> assertEquals(ListItem.class, section.getChildren().get(10).getClass(), "Verifica list item"),
				() -> assertEquals("Cidades", ((Combobox) section.getChildren().get(12)).getValue(), "Verifica Combobox de cidades")
				
				// testar combobox
				
				);
	}

	@Test
	void testLevels() throws Exception {
		readFile("/grammar-levels.mplf");
		Section firstSection = documento.getSectionList().get(0);
		
		System.out.println(firstSection.getChildren());
		
		assertAll(
				() -> assertEquals(2, documento.getSectionList().size(), "It contains 2 main sections"),
				() -> assertEquals("main", documento.getSectionList().get(0).getId(), "Section name = main"),
				() -> assertEquals("root2", documento.getSectionList().get(1).getId(), "Section name = root2"),
				() -> assertEquals(5, documento.getSectionList().get(0).getChildren().size(), "Section 'main' have 5 elems"),
				() -> assertEquals("level1.2", getLabel(firstSection.getChildren().get(1)), "First section label 1.2"),
				() -> assertEquals(Section.class, firstSection.getChildren().get(2).getClass(), "Element #2 type is Section "),
				() -> assertEquals(2, ((Section) firstSection.getChildren().get(2)).getChildren().size(), "Section have 2 children"),
				() -> assertEquals(Section.class, firstSection.getChildren().get(3).getClass(), "Element #3 type is Section "),
				() -> assertEquals(2, ((Section) firstSection.getChildren().get(3)).getChildren().size()),
				() -> assertEquals("level1.3", getLabel(firstSection.getChildren().get(4)))
				);
	}

	private String getLabel(Element element) {
		return ((Label) element).getValue();
	}

	@Test
	void testTables() throws Exception {
		readFile("/grammar-table.mplf");
		Section section = documento.getSectionList().get(0);
		
		assertEquals(Table.class, section.getChildren().get(1).getClass());
		Table firstTable = (Table) section.getChildren().get(1);
		
		assertAll(
				() -> assertEquals(2, firstTable.getColumnList().size(), "First table should have 2 columns"),
				() -> assertEquals("cidade", firstTable.getColumnList().get(1).getName(), "Column name = cidade")
				);
		
		assertEquals(Table.class, ((Table) section.getChildren().get(1)).getClass());
		Table secTable = (Table) ((Section) section.getChildren().get(2)).getChildren().get(0);
		
		assertAll(
				() -> assertEquals(2, secTable.getColumnList().size(), "Second table have 2 columns"),
				() -> assertEquals("idade", secTable.getColumnList().get(1).getName(), "Column name = idade"),
				() -> assertNotNull(secTable.getColumnList().get(0).getRowList(), "Row list not null in a column"),
				() -> assertEquals("ricardo", secTable.getColumnList().get(0).getRowList().get(0).getData(), "Check Row data value")
				);
	}
	
	@Test
	void testWithTemplate() throws Exception {
		readFile("/grammar-template.mplf");
		Section section = documento.getSectionList().get(0);
		
		assertAll(
				() -> assertEquals("body", section.getId()),
				() -> assertNotNull(documento.getTempĺate()),
				() -> assertEquals("main", documento.getTempĺate().getSectionList().get(0).getId())
				);
		
	}
	
	@Test
	void testFile() throws Exception {
		InputStream is = Files.newInputStream(Paths.get("/mnt/Dados/projetos/pml/src/test/resources/grammar-ok.mplf"));
		assertNotNull(is);
	}
}
