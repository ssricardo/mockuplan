package org.rss.tools.mpl.domain.element;

import org.rss.tools.mpl.domain.Element;

public class ListItem implements Element {

	private String value;

	private String styleClass;

	public ListItem() {
		super();
	}

	public ListItem(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}

	@Override
	public String toString() {
		return "ListItem [" + value + "]";
	}

}
