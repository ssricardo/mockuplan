package org.rss.tools.mpl.render.wrapper;

import org.rss.tools.mpl.domain.element.ListItem;
import org.rss.tools.mpl.render.DocumentVisitor;
import org.rss.tools.mpl.render.Rendered;

/**
 * @see
 * 	org.rss.tools.mpl.render.wrapper
 * @author ricardo
 */
public class ListItemDecorator implements Rendered {

	private ListItem wrapped;

	public ListItemDecorator(ListItem source) {
		this.wrapped = source;
	}

	public void accept(DocumentVisitor visitor) {
		visitor.visit(this.wrapped);
	}
}