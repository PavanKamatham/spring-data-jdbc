package com.learning.java.exception;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Class that can be used to handle errors across the application.
 */
@ControllerAdvice
public class JDBCExceptionHandler extends ResponseEntityExceptionHandler {
	Logger logger = LoggerFactory.getLogger(JDBCExceptionHandler.class);

	/**
	 * Method to handle RecordNotFoundException that thrown across the application.
	 * 
	 * @param {@link RecordNotFoundException} the exception occurred in application.
	 * @param {@link {@link WebRequest}} the request
	 * @return {@link ResponseEntity} the api response
	 */
	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException cnfe, WebRequest request) {
		List<DetailError> detailErrors = new ArrayList<>();

		DetailError detailError = new DetailError();
		detailError.setCode(cnfe.getMessage());
		detailError.setDomain(cnfe.getDomain());
		detailError.setField(cnfe.getField());
		if (null != cnfe.getCause()) {
			detailError.setMessage(String.valueOf(cnfe.getCause()));
		}
		detailErrors.add(detailError);
		ErrorResponse errorResponse = new ErrorResponse(String.valueOf(HttpStatus.NOT_FOUND.value()), "NoRecordFound",
				"spring-data-jdbc", detailErrors);
		return new ResponseEntity<Object>(errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

	/**
	 * * Method to handle RequestException that thrown across the application.
	 * 
	 * @param {@link RequestException} the exception occurred in application.
	 * @param {@link {@link WebRequest}} the request
	 * @return {@link ResponseEntity} the api response
	 */
	@ExceptionHandler(RequestException.class)
	public ResponseEntity<Object> handleRequestException(RequestException re, Exception ex) {
		List<DetailError> detailErrors = new ArrayList<>();

		DetailError detailError = new DetailError();
		detailError.setCode(re.getMessage());
		detailError.setDomain(re.getDomain());
		detailError.setField(re.getField());

		if (null != re.getCause()) {
			detailError.setMessage(String.valueOf(re.getCause()));
		}
		detailErrors.add(detailError);
		ErrorResponse errorResponse = new ErrorResponse(String.valueOf(HttpStatus.BAD_REQUEST.value()),
				"InvalidRequestParameter", "spring-data-jdbc", detailErrors);
		return new ResponseEntity<Object>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);

	}
}
