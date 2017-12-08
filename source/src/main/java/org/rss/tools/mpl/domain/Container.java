package org.rss.tools.mpl.domain;

import java.util.List;

/** An element that accepts other children elements */
public interface Container<E extends Element> extends Element {

	List<E> getChildren();
	
	void addChild(E element);
}
