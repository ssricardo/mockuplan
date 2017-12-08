package org.rss.tools.mpl.domain.element;

import java.util.ArrayList;
import java.util.List;

import org.rss.tools.mpl.domain.Container;
import org.rss.tools.mpl.domain.Element;

/** Column of a table */
public class Column implements Container<RowData>, Element {

	private String name;
	
	private List<RowData> rowList;
	
	public Column() {
		super();
	}

	public Column(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RowData> getRowList() {
		return rowList;
	}

	public void setRowList(List<RowData> rowList) {
		this.rowList = rowList;
	}

	@Override
	public String toString() {
		return "Column [" + name + "]";
	}

	@Override
	public List<RowData> getChildren() {
		return getRowList();
	}

	@Override
	public void addChild(RowData element) {
		if (rowList == null) {
			rowList = new ArrayList<>();
		}
		this.rowList.add(element);
	}
	
}
