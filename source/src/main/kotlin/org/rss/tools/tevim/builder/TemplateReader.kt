package org.rss.tools.tevim.builder

import org.rss.tools.tevim.domain.Document
import org.rss.tools.tevim.global.ServiceProvider
import org.rss.tools.tevim.parsing.Parser
import org.rss.tools.tevim.reader.Reader
import org.rss.tools.tevim.reader.TemplateProvider

/**
 * Responsible for loading and processing a parent document
 */
object TemplateReader {

    private val fileReader: Reader = ServiceProvider.get(Reader::class.java)

    /**
     * tries to read based on a TemplateProvider
     */
    fun process(document: Document, provider: TemplateProvider) {
        document.templateRef?.let {ref ->
            val baseDoc = provider.provideTemplates()[ref]
                    ?: throw IllegalArgumentException("Template was not found: $ref")
            document.template = baseDoc
        }
    }

    /**
     * tries to read based on a file location
     */
    fun process(parser: Parser<Document>, document: Document, filePath: String) {
        val baseDoc = parser.parse(fileReader.read(filePath))
        document.template = baseDoc
    }
}