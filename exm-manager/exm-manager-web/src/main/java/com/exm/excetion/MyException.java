package com.exm.excetion;

public class MyException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;

	public MyException()
	{
	}

	public MyException(String msg)
	{
		this.msg = msg;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

}
