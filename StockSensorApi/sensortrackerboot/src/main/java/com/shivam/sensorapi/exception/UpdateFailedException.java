package com.shivam.sensorapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_MODIFIED,reason="Could not update/create data!")
public class UpdateFailedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
