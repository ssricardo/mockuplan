package org.rss.tools.mpl.global;

/**
 * Holds constants for Program parameters
 * 
 * @author ricardo saturnino
 *
 */
public enum Parameter {
	
	// Remember to update params.md altogether with this

	INPUT("-i", true), 
	OUTPUT("-o", true), 
	HELP("-help", false), 
	TEMP("-temp", false), 
	SHOW_BROWSER("-show", false),

	// ---------------------------------------------------

	DEBUG("-debug", false),

	TRACE("-trace", false);

	private final String text;
	
	private final boolean valueRequired;

	private Parameter(String text, boolean required) {
		this.text = text;
		this.valueRequired = required;
	}

	public static Parameter getInstance(String value) {
		for (Parameter param : values()) {
			if (param.getText().equals(value)) {
				return param;
			}
		}
		throw new UnsupportedOperationException("Parameter not supported: " + value);
	}

	public String getText() {
		return text;
	}

	public boolean isValueRequired() {
		return valueRequired;
	}
	
}
