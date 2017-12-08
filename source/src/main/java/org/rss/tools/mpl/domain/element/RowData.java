package org.rss.tools.mpl.domain.element;

import org.rss.tools.mpl.domain.Element;

/** Holds some sample data to fill elements in a View */
public class RowData implements Element {

	private Object data;

	public RowData() {
		super();
	}
	
	public RowData(Object data) {
		super();
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "RowData [" + data + "]";
	}
	
}
