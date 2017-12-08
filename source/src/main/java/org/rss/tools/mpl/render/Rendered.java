package org.rss.tools.mpl.render;

/**
 * Indicates a component that can be rendered. 
 * Used to decorate original elements
 * @author ricardo
 *
 */
public interface Rendered {

	void accept(DocumentVisitor visitor);
}
