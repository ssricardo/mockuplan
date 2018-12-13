package org.rss.tools.mpl.reader

import org.rss.tools.mpl.domain.Document

/**
 * Indicates existing documents to be using as templates.
 * @see TemplateProcessor
 * */
interface TemplateProvider {

    /**
     * Must provide a list of documents associated with a String ID
     */
    fun provideTemplates(): Map<String, Document>
}