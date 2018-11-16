package org.rss.tools.mpl;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.rss.tools.mpl.domain.Document;
import org.rss.tools.mpl.global.Parameter;
import org.rss.tools.mpl.global.ServiceProvider;
import org.rss.tools.mpl.parsing.MplDocumentParser;
import org.rss.tools.mpl.parsing.Parser;
import org.rss.tools.mpl.reader.FileReader;
import org.rss.tools.mpl.reader.Reader;
import org.rss.tools.mpl.render.DocumentVisitor;
import org.rss.tools.mpl.render.FileRenderer;
import org.rss.tools.mpl.render.RenderResponse;
import org.rss.tools.mpl.render.RenderedFactory;
import org.rss.tools.mpl.render.RendererType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Entry point in the application. Responsible for handle parameters and call
 * phases
 * 
 * @author ricardo saturnino
 *
 */
public class Main {
	
	private static final String TEMP_DIRECTORY_NAME = "mockuplan";
	private static final String PARAM_FILE_REF = "/params.md";
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);
	
	private static final Parameter[] REQUIRED_PARAMS = { Parameter.INPUT };
	
	static {
		registerDefaultProviders();
	}

	public static void main(String[] input) throws Exception {

		if (showHelp(input)) {
			return;
		}
		
		// -----------------------------------------------------------
		
		Map<Parameter, String> args = readArguments(input);
		
		{
			String currentPath = args.get(Parameter.INPUT);
			ServiceProvider.INSTANCE.register(Reader.class, new FileReader(Paths.get(currentPath).getParent()));
		}

		// ------------------------------------------------------------
		Document document = processParse(args.get(Parameter.INPUT));
		List<RenderResponse> response = processRender(document);

		// ---------------------------- output ------------------------

		Path outputDir = null;
		String fileName = Paths.get(args.get(Parameter.INPUT)).getFileName().toString();
		fileName = fileName.substring(0, fileName.indexOf("."));

		if (!args.containsKey(Parameter.OUTPUT)) {
			outputDir = Files.createTempDirectory(TEMP_DIRECTORY_NAME);
		} else {
			outputDir = Paths.get(args.get(Parameter.OUTPUT));
		}

		Path responseFile = new FileRenderer(response, outputDir, fileName).render();

		if (args.containsKey(Parameter.SHOW_BROWSER)) {
			if (responseFile == null) {
				LOG.warn("Nothing to show");
				return;
			}

			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(responseFile.toUri());
			} else {
				LOG.error("It was not possible to open Browser with created file");
			}
		}
	}

	private static Map<Parameter, String> readArguments(String[] args) {
		final Map<Parameter, String> params = new HashMap<>();

		Parameter lastKey = null;
		for (int i = 0; i < args.length; i++) {
			final String anArg = args[i];

			if (anArg.charAt(0) == '-') {
				if (anArg.length() < 2) {
					throw new IllegalArgumentException("Error at argument " + anArg);
				}

				lastKey = Parameter.Companion.getInstance(anArg);
				params.put(lastKey, null);
			} else if (lastKey != null) {
				params.put(lastKey, anArg);
			} else {
				throw new IllegalArgumentException("Illegal parameter usage" + anArg);
			}
		}

		for (Parameter req : REQUIRED_PARAMS) {
			if (!params.keySet().contains(req)) {
				throw new IllegalStateException("A mandatory parameter was not specified: " + req);
			} else if (params.get(req).isEmpty()) {
				throw new IllegalStateException("A mandatory parameter contains no value: " + req);
			}
		}
		
		for (Entry<Parameter, String> entry : params.entrySet()) {
			if (entry.getKey().isValueRequired()) {
				if (entry.getValue() == null) {
					throw new IllegalArgumentException("A parameter was specified by its value is missing: " + entry.getKey().getText());
				}
			}
		}
		return params;
	}

	private static boolean showHelp(String[] args) {
		if (args == null || args.length == 0) {
			return false;
		}
		for (int i = 0; i < args.length; i++) {
			if (Parameter.HELP.getText().equals(args[i])) {

				try (InputStream resource = Main.class.getResourceAsStream(PARAM_FILE_REF)) {
					LOG.info("Showing help...");
					new BufferedReader(new InputStreamReader(resource, StandardCharsets.UTF_8)).lines()
							.forEach(line -> System.out.println(line));
				} catch (IOException e) {
					LOG.error("An error ocurred. It was not possible to read help contents");
					e.printStackTrace();
				}

				return true;
			}
		}
		return false;
	}

	// careful on change - review Logger aspect
	private static List<RenderResponse> processRender(Document documento) throws Exception {
		DocumentVisitor renderer = ServiceProvider.INSTANCE.get(DocumentVisitor.class);
		renderer.visit(documento);
		return renderer.getResult();
	}

	// careful on change - review Logger
	private static Document processParse(String inputFile) throws IOException {
		@SuppressWarnings("unchecked")
		Parser<Document> parser = ServiceProvider.INSTANCE.get(Parser.class);
		InputStream is = ServiceProvider.INSTANCE.get(Reader.class).read(inputFile);
		Document documento = parser.parse(is);
		return documento;
	}
	
	/** Registers default services
		Call this method later to override default instances
	 * */
	public static void registerDefaultProviders() {
		LOG.debug("registerDefaultProviders()");
		
		ServiceProvider.INSTANCE.register(Parser.class, MplDocumentParser.getInstance());
		ServiceProvider.INSTANCE.register(DocumentVisitor.class,
				RenderedFactory.getInstance().getRenderer(RendererType.HTML_TEMPLATE_STATE));
	}
}
