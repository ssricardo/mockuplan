package org.rss.tools.mpl.domain.element;

import org.rss.tools.mpl.domain.Element;

public class LineHeader implements Element {

	private String value;
	
	private Integer level;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "LineHeader [" + level + ", " + value + "]";
	}
	
	
}
