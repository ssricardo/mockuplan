package org.rss.tools.mpl.domain.element;

import org.rss.tools.mpl.domain.Element;

public class Label implements Element {

	private String value;
	
	public Label() {
		super();
	}

	public Label(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Label [" + value + "]";
	}
	
	
}
