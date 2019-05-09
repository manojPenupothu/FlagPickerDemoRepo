package com.flagpicker.FlagPickerDemo.helpers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author manoj penupothu
 * This class is created to post success or failure 
 * messages to the front end
 */
@ResponseStatus(HttpStatus.CREATED)
public class HelperMessage {

	private String message;

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public HelperMessage(String message)
	{
		this.message = message;
	}

	@Override
	public String toString()
	{
		return "HelperMessage [message=" + message + "]";
	}
	
	
}
