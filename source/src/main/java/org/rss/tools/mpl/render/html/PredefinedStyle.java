package org.rss.tools.mpl.render.html;

/**
 * Predefined style classes
 * @author ricardo saturnino
 *
 */
public enum PredefinedStyle {
	DEFAULT("/css/default.css", "default.css"),
	ANDROID("/css/default.css", "default.css"),		// TODO create this configuration
	IOS("/css/default.css", "default.css")			// TODO create this configuration
	;
	
	final String internalRef;

	final String fileName;

	private PredefinedStyle(String resourceRef, String fileName) {
		this.internalRef = resourceRef;
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public String getInternalRef() {
		return internalRef;
	}
	
}