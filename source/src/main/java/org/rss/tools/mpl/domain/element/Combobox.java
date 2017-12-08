package org.rss.tools.mpl.domain.element;

import org.rss.tools.mpl.domain.Element;

public class Combobox implements Element {

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Combobox [" + value + "]";
	}
	
}
