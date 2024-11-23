package com.learning.java.exception;

/**
 * Class to be used for record not found exception.
 */
public class RecordNotFoundException extends RuntimeException {

	/**
	 * Generated SerialVersionUID
	 */
	private static final long serialVersionUID = 5113898862708029814L;

	private String field;
	private String domain;

	public RecordNotFoundException(String message, String field, String domain, Throwable cause) {
		super(message, cause);
		this.field = field;
		this.domain = domain;
	}

	public RecordNotFoundException(String message, String field, String domain) {
		super(message);
		this.field = field;
		this.domain = domain;
	}

	public RecordNotFoundException(String field, String domain, Throwable cause) {
		super(cause);
		this.field = field;
		this.domain = domain;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
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

}
