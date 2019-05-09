package com.flagpicker.FlagPickerDemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * @author manoj penupothu
 *
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DatabaseInsertionException extends RuntimeException {

	public DatabaseInsertionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
