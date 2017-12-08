package org.rss.tools.mpl.render.wrapper;

import org.rss.tools.mpl.domain.element.Label;
import org.rss.tools.mpl.render.DocumentVisitor;
import org.rss.tools.mpl.render.Rendered;

/**
 * @see
 * 	org.rss.tools.mpl.render.wrapper
 * @author ricardo
 */
public class LabelDecorator implements Rendered {

	private Label wrapped;

	public LabelDecorator(Label source) {
		this.wrapped = source;
	}

	public void accept(DocumentVisitor visitor) {
		visitor.visit(this.wrapped);
	}
}