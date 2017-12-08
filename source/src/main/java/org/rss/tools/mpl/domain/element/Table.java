package org.rss.tools.mpl.domain.element;

import java.util.ArrayList;
import java.util.List;

import org.rss.tools.mpl.domain.Container;

public class Table implements Container<Column> {

	private String id;
	
	private final List<Column> columnList = new ArrayList<>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Column> getColumnList() {
		return columnList;
	}

	@Override
	public String toString() {
		return "Table [" + id + ", columns=" + columnList + "]";
	}

	@Override
	public List<Column> getChildren() {
		return getColumnList();
	}

	@Override
	public void addChild(Column element) {
		columnList.add(element);
	}
	
}
