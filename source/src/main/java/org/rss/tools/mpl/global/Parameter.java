package org.rss.tools.mpl.global;

/**
 * Holds constants for Program parameters
 * 
 * @author ricardo saturnino
 *
 */
public enum Parameter {
	
	// Remember to update params.md altogether with this

	INPUT("-i"), 
	OUTPUT("-o"), 
	HELP("-help"), 
	TEMP("-temp"), 
	SHOW_BROWSER("-show"),

	// ---------------------------------------------------

	DEBUG("-debug"),

	TRACE("-trace"),;

	private final String text;

	private Parameter(String text) {
		this.text = text;
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

}
