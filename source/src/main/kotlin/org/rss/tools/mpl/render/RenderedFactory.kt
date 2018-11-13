package org.rss.tools.mpl.render

import org.rss.tools.mpl.render.html.HtmlRenderer
import org.rss.tools.mpl.render.html.StatesHtmlRenderer
import org.rss.tools.mpl.render.html.TemplateHtmlRenderer

/**
 * Factory to retrieve the correct renderer implementation
 * @author ricardo saturnino
 */
class RenderedFactory private constructor() {

    /**
     * Gets the instance of a Document Renderer
     * @param rType type specification
     * @throws Exception
     */
    fun getRenderer(rType: RendererType): DocumentVisitor {
        return when (rType) {
            RendererType.HTML -> HtmlRenderer()
            RendererType.STATED_HTML -> StatesHtmlRenderer(HtmlRenderer())
            RendererType.TEMPLATED_HTML -> TemplateHtmlRenderer(HtmlRenderer())
            RendererType.HTML_TEMPLATE_STATE -> StatesHtmlRenderer(TemplateHtmlRenderer(HtmlRenderer()))
            else -> {
                throw IllegalStateException("No render found")
            }
        }
    }

    companion object {

        @JvmStatic
        val instance by lazy {
            RenderedFactory()
        }
    }

}
