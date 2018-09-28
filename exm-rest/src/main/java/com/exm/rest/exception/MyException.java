package com.exm.rest.exception;

public class MyException extends Exception
{
	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	private String message;
	

}
