package org.rss.tools.mpl.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Containers elements. The only allowed children directly in {@link Document}
 */
public class Section implements Container<Element> {

	private String id;
	private String styleClass;
	private final List<Element> children = new ArrayList<>();

	public Section() {
		super();
	}
	
	public Section(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStyleClass() {
		return styleClass;
	}

	public void setStyleClass(String styleClass) {
		this.styleClass = styleClass;
	}
	
	public List<Element> getChildren() {
		return children;
	}

	public void addChild(Element elem) {
		this.children.add(elem);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Section other = (Section) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Section [" + id + ", styleClass=" + styleClass + "]";
	}

}
