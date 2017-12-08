package org.rss.tools.mpl.render;

import org.rss.tools.mpl.render.html.HtmlRenderer;
import org.rss.tools.mpl.render.html.StatesHtmlRenderer;
import org.rss.tools.mpl.render.html.TemplateHtmlRenderer;

/**
 * Factory to retrieve the correct renderer implementation
 * @author ricardo saturnino
 */
public class RenderedFactory {
	
	private static RenderedFactory instance;

	private RenderedFactory() {
	}

	public static RenderedFactory getInstance() {
		if (null == instance) {
			instance = new RenderedFactory();
		}
		return instance;
	}

	/**
	 * Gets the instance of a Document Renderer
	 * @param rType type specification
	 * @throws Exception
	 */
	public DocumentVisitor getRenderer(RendererType rType) {

		switch (rType) {
		case HTML:
			return new HtmlRenderer();
		case STATED_HTML:
			return new StatesHtmlRenderer(new HtmlRenderer());
		case TEMPLATED_HTML:
			return new TemplateHtmlRenderer(new HtmlRenderer());
		case HTML_TEMPLATE_STATE:
			return new StatesHtmlRenderer(new TemplateHtmlRenderer(new HtmlRenderer()));
		default:
			break;

		}

		throw new IllegalStateException("No render found");
		
	}

	
}
