package org.rss.tools.mpl.render.wrapper;

import org.rss.tools.mpl.domain.element.LineHeader;
import org.rss.tools.mpl.render.DocumentVisitor;
import org.rss.tools.mpl.render.Rendered;

/**
 * @see
 * 	org.rss.tools.mpl.render.wrapper
 * @author ricardo
 */
public class LineHeaderDecorator implements Rendered {

	private LineHeader wrapped;

	public LineHeaderDecorator(LineHeader source) {
		this.wrapped = source;
	}

	public void accept(DocumentVisitor visitor) {
		visitor.visit(this.wrapped);
	}
}