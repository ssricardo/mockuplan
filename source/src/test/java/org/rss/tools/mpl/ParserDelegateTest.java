package org.rss.tools.mpl;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.rss.tools.mpl.domain.Document;
import org.rss.tools.mpl.global.ServiceProvider;
import org.rss.tools.mpl.parsing.MplDocumentParser;
import org.rss.tools.mpl.parsing.Parser;
import org.rss.tools.mpl.render.DocumentVisitor;

public class ParserDelegateTest {

	@BeforeAll
	public static void setup() {
		Main.registerDefaultProviders();
	}
	
	@Test
	void testDefault() throws Exception {
		DocumentVisitor render = ServiceProvider.get(DocumentVisitor.class);
		Parser parser = ServiceProvider.get(Parser.class);
		
		assertNotNull(render);
		assertTrue(parser instanceof MplDocumentParser);
		
		testOverrideParser();
	}
	
	// Needs to be executed in sequence. Otherwise it breaks the default test
//	@Test
	void testOverrideParser() throws Exception {
		ServiceProvider.register(Parser.class, new MyParser());
		Parser parser = ServiceProvider.get(Parser.class);
		
		assertNotNull(parser);
		assertNotEquals(MplDocumentParser.getInstance(), parser);
	}
	
	class MyParser implements Parser<Document>{

		@Override
		public Document parse(InputStream input) throws IOException {
			return null;
		}
		
	}

}
