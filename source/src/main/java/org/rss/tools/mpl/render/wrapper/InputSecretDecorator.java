package org.rss.tools.mpl.render.wrapper;

import org.rss.tools.mpl.domain.element.InputSecret;
import org.rss.tools.mpl.render.DocumentVisitor;
import org.rss.tools.mpl.render.Rendered;

/**
 * @see
 * 	org.rss.tools.mpl.render.wrapper
 * @author ricardo
 */
public class InputSecretDecorator implements Rendered {

	private InputSecret wrapped;

	public InputSecretDecorator(InputSecret source) {
		this.wrapped = source;
	}

	public void accept(DocumentVisitor visitor) {
		visitor.visit(this.wrapped);
	}
}