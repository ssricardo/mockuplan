package org.rss.tools.mpl.render.wrapper;

import org.rss.tools.mpl.domain.element.InputText;
import org.rss.tools.mpl.render.DocumentVisitor;
import org.rss.tools.mpl.render.Rendered;

/**
 * @see
 * 	org.rss.tools.mpl.render.wrapper
 * @author ricardo
 */
public class InputTextDecorator implements Rendered {

	private InputText wrapped;

	public InputTextDecorator(InputText source) {
		this.wrapped = source;
	}

	public void accept(DocumentVisitor visitor) {
		visitor.visit(this.wrapped);
	}
}