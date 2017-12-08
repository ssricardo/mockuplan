package org.rss.tools.mpl.render.wrapper;

import org.rss.tools.mpl.domain.element.Radiobox;
import org.rss.tools.mpl.render.DocumentVisitor;
import org.rss.tools.mpl.render.Rendered;

/**
 * @see
 * 	org.rss.tools.mpl.render.wrapper
 * @author ricardo
 */
public class RadioboxDecorator implements Rendered {

	private Radiobox wrapped;

	public RadioboxDecorator(Radiobox source) {
		this.wrapped = source;
	}

	public void accept(DocumentVisitor visitor) {
		visitor.visit(this.wrapped);
	}
}