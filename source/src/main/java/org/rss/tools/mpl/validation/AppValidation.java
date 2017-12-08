package org.rss.tools.mpl.validation;

/**
 * Holds validation rules of the application
 * @author ricardo saturnino
 *
 */
public enum AppValidation {

	INVALID_STATE("System is in invalid state while trying to parse", null), 
	INCORRECT_INDENTATION("Incorrect indentation", 
			"Inner level must be only ONE Tab more than previous level. Probably there are 2 or more tab difference in the line."),
	INVALID_CONTAINER_LINE("Unexpected new level. Previous line should contain only simple text to be considered as Section.", 
			"Remove extra elements after the label. Or Remove sub level."), 
	TABLE_NO_ID("Table container without mandatory ID", "Add an identificator between | and |");
	
	private final String message;
	
	private final String howFix;

	private AppValidation(String message, String howFix) {
		this.message = message;
		this.howFix = howFix;
	}

	public String getMessage() {
		return message;
	}

	public String getHowFix() {
		return howFix;
	}
	
	
}
