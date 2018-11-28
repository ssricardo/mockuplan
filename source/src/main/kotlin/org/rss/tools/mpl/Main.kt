package org.rss.tools.mpl

import org.rss.tools.mpl.builder.DocumentBuilder
import org.rss.tools.mpl.domain.Document
import org.rss.tools.mpl.global.Parameter
import org.rss.tools.mpl.global.ServiceProvider
import org.rss.tools.mpl.parsing.Parser
import org.rss.tools.mpl.reader.FileReader
import org.rss.tools.mpl.reader.Reader
import org.rss.tools.mpl.render.*
import org.slf4j.LoggerFactory

import java.awt.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.HashMap

/**
 * Entry point in the application. Responsible for handling parameters and call
 * phases
 *
 * @author ricardo saturnino
 */
object Main {

    private const val TEMP_DIRECTORY_NAME = "mockuplan"
    private const val PARAM_FILE_REF = "/params.md"
    private val LOG = LoggerFactory.getLogger(Main::class.java)

    private val REQUIRED_PARAMS = arrayOf(Parameter.INPUT)

    init {
        registerDefaultProviders()
    }

    @Throws(Exception::class)
    @JvmStatic
    fun main(input: Array<String>) {

        if (showHelp(input)) {
            return
        }

        // -----------------------------------------------------------

        val args = readArguments(input)

        val currentPath = args[Parameter.INPUT] ?:
            throw IllegalArgumentException("Parameter INPUT path must be provided")
        ServiceProvider.register(Reader::class.java, FileReader(Paths.get(currentPath).parent))

        // ------------------------------------------------------------
        val document = processParse(currentPath)
        val response = processRender(document)

        // ---------------------------- output ------------------------

        var outputDir: Path? = null
        var fileName = Paths.get(args[Parameter.INPUT]).fileName.toString()
        fileName = fileName.substring(0, fileName.indexOf("."))

        if (!args.containsKey(Parameter.OUTPUT)) {
            outputDir = Files.createTempDirectory(TEMP_DIRECTORY_NAME)
        } else {
            outputDir = Paths.get(args[Parameter.OUTPUT])
        }

        val responseFile = FileRenderer(response, outputDir!!, fileName).render()

        if (args.containsKey(Parameter.SHOW_BROWSER)) {
            if (responseFile == null) {
                LOG.warn("Nothing to show")
                return
            }

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(responseFile.toUri())
            } else {
                LOG.error("It was not possible to open Browser with created file")
            }
        }
    }

    private fun readArguments(args: Array<String>): Map<Parameter, String> {
        val params = HashMap<Parameter, String?>()

        var lastKey: Parameter? = null
        for (i in args.indices) {
            val anArg = args[i]

            if (anArg[0] == '-') {
                if (anArg.length < 2) {
                    throw IllegalArgumentException("Error at argument $anArg")
                }

                lastKey = Parameter.getInstance(anArg)
                params[lastKey] = null
            } else if (lastKey != null) {
                params[lastKey] = anArg
            } else {
                throw IllegalArgumentException("Illegal parameter usage$anArg")
            }
        }

        for (req in REQUIRED_PARAMS) {
            if (!params.keys.contains(req)) {
                throw IllegalStateException("A mandatory parameter was not specified: $req")
            } else if (params[req]?.isEmpty() != false) {
                throw IllegalStateException("A mandatory parameter contains no value: $req")
            }
        }

        for ((key, value) in params) {
            if (key.isValueRequired) {
                if (value == null) {
                    throw IllegalArgumentException("A parameter was specified by its value is missing: " + key.text)
                }
            }
        }
        return params.filterValues { it != null }.mapValues { it.value as String }
    }

    private fun showHelp(args: Array<String>?): Boolean {
        if (args == null || args.size == 0) {
            return false
        }
        for (i in args.indices) {
            if (Parameter.HELP.text == args[i]) {

                try {
                    Main::class.java.getResourceAsStream(PARAM_FILE_REF).use { resource ->
                        LOG.info("Showing help...")
                        BufferedReader(InputStreamReader(resource, StandardCharsets.UTF_8)).lines()
                                .forEach { line -> println(line) }
                    }
                } catch (e: IOException) {
                    LOG.error("An error ocurred. It was not possible to read help contents")
                    e.printStackTrace()
                }

                return true
            }
        }
        return false
    }

    // careful on changing it - review Logger aspect!
    @Throws(Exception::class)
    private fun processRender(documento: Document): List<RenderResponse> {
        val renderer = ServiceProvider[DocumentVisitor::class.java]
        renderer.visit(documento)
        return renderer.result
    }

    // careful on changing it - review Logger
    @Throws(IOException::class)
    private fun processParse(inputFile: String): Document {
        val parser: Parser<Document> = ServiceProvider[Parser::class.java] as? Parser<Document>
                ?: throw IllegalStateException("No parser for Document was found.")
        val iStream= ServiceProvider[Reader::class.java].read(inputFile)
        return parser.parse(iStream)
    }

    /** Registers default services
     * Call this method later to override default instances
     */
    fun registerDefaultProviders() {
        LOG.debug("registerDefaultProviders()")

        ServiceProvider.register(Parser::class.java, DocumentBuilder)
        ServiceProvider.register(DocumentVisitor::class.java,
                RenderedFactory.instance.getRenderer(RendererType.HTML_TEMPLATE_STATE))
    }
}
