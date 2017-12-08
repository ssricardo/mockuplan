package org.rss.tools.mpl.render.wrapper;

import org.rss.tools.mpl.domain.element.Table;
import org.rss.tools.mpl.render.DocumentVisitor;
import org.rss.tools.mpl.render.Rendered;

/**
 * @see
 * 	org.rss.tools.mpl.render.wrapper
 * @author ricardo
 */
public class TableDecorator implements Rendered {

	private Table wrapped;

	public TableDecorator(Table source) {
		this.wrapped = source;
	}

	public void accept(DocumentVisitor visitor) {
		visitor.visit(this.wrapped);
	}
}