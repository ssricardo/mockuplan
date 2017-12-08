package org.rss.tools.mpl.domain.element;

import org.rss.tools.mpl.domain.Element;

public class Button implements Element {

	private String value;

	public Button(String value) {
		super();
		this.value = value;
	}

	public Button() {
		super();
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Button [" + value + "]";
	}

}
