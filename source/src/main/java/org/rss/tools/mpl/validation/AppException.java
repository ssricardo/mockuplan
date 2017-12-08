package org.rss.tools.mpl.validation;

/**
 * ApplicationException. Contains reference to AppErrors and parameter
 * @author ricardo saturnino
 * 
 */
public class AppException extends IllegalStateException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private AppValidation error;
	
	private Object[] parameters;

	public AppException() {
		super();
	}

	public AppException(AppValidation error, Throwable cause) {
		super(error.getMessage(), cause);
		this.error = error;
	}

	public AppException(AppValidation error, Object... params) {
		super();
		this.error = error;
		this.parameters = params;
	}

	public AppValidation getError() {
		return error;
	}

	public void setError(AppValidation error) {
		this.error = error;
	}

	public Object[] getParameters() {
		return parameters;
	}

	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}

}
