package org.rss.tools.mpl.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents root document
 */
public class Document {

	private Document tempĺate;
	
	private List<String> stateList;
	
	private final List<String> styleList = new ArrayList<>();
	
	private final List<Section> sectionList = new ArrayList<>();
	
	public Document getTempĺate() {
		return tempĺate;
	}

	public void setTempĺate(Document tempĺate) {
		this.tempĺate = tempĺate;
	}

	public List<String> getStateList() {
		return stateList;
	}

	public void setStateList(List<String> stateList) {
		this.stateList = stateList;
	}

	public List<String> getStyleList() {
		return styleList;
	}
	
	public void addStyleFile(String item) {
		styleList.add(item);
	}
	
	public List<Section> getSectionList() {
		return sectionList;
	}

	public void addSection(Section sec) {
		this.sectionList.add(sec);
	}

	@Override
	public String toString() {
		return "Document [tempĺate=" + tempĺate + ", states=" + stateList + ", styles=" + styleList + "]";
	}
	
}
