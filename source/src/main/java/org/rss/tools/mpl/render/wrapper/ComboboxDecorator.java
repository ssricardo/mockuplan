package org.rss.tools.mpl.render.wrapper;

import org.rss.tools.mpl.domain.element.Combobox;
import org.rss.tools.mpl.render.DocumentVisitor;
import org.rss.tools.mpl.render.Rendered;

/**
 * @see
 * 	org.rss.tools.mpl.render.wrapper
 * @author ricardo saturnino
 */
public class ComboboxDecorator implements Rendered {

	private Combobox wrapped;

	public ComboboxDecorator(Combobox source) {
		this.wrapped = source;
	}

	public void accept(DocumentVisitor visitor) {
		visitor.visit(this.wrapped);
	}
}