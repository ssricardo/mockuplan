package org.rss.tools.mpl.render.wrapper;

import org.rss.tools.mpl.domain.element.InputEmail;
import org.rss.tools.mpl.render.DocumentVisitor;
import org.rss.tools.mpl.render.Rendered;

/**
 * @see
 * 	org.rss.tools.mpl.render.wrapper
 * @author ricardo
 */
public class InputEmailDecorator implements Rendered {

	private InputEmail wrapped;

	public InputEmailDecorator(InputEmail source) {
		this.wrapped = source;
	}

	public void accept(DocumentVisitor visitor) {
		visitor.visit(this.wrapped);
	}
}