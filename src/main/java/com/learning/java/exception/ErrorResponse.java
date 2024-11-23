package com.learning.java.exception;

import java.util.List;

/**
 * Represents an Error in application.
 */
public class ErrorResponse {
	/** The error code **/
	private String code;
	/** The error message **/
	private String message;
	/** The domain name error occurred **/
	private String domain;
	/** The detail error message **/
	private List<DetailError> detailErrors;

	public ErrorResponse(String code, String message, String domain, List<DetailError> detailErrors) {
		this.code = code;
		this.message = message;
		this.domain = domain;
		this.detailErrors = detailErrors;
	}

	public ErrorResponse(String code, String message, String domain) {
		this.code = code;
		this.message = message;
		this.domain = domain;
	}

	public ErrorResponse() {

	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the domain
	 */
	public String getDomain() {
		return domain;
	}

	/**
	 * @param domain the domain to set
	 */
	public void setDomain(String domain) {
		this.domain = domain;
	}

	/**
	 * @return the detailErrors
	 */
	public List<DetailError> getDetailErrors() {
		return detailErrors;
	}

	/**
	 * @param detailErrors the detailErrors to set
	 */
	public void setDetailErrors(List<DetailError> detailErrors) {
		this.detailErrors = detailErrors;
	}

}
