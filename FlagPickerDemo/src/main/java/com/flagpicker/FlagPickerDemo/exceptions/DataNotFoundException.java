package com.flagpicker.FlagPickerDemo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * @author manoj penupothu
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DataNotFoundException extends RuntimeException {

	public DataNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
}
