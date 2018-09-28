package com.exm.common.model;

public class ResponseResultVo
{

	private Integer status;
	private String msg;
	private Object data;

	
	public ResponseResultVo()
	{
	}

	
	public ResponseResultVo(Integer status, Object data)
	{
		super();
		this.status = status;
		this.data = data;
	}


	public ResponseResultVo(Integer status, String msg, Object data)
	{
		this.msg = msg;
		this.status = status;
		this.data = data;
	}

	public ResponseResultVo(Integer status, String msg)
	{
		this.msg = msg;
		this.status = status;
	}

	public static ResponseResultVo build(Integer status, String msg, Object data)
	{
		return new ResponseResultVo(status, msg, data);
	}

	public static ResponseResultVo build(Integer status, String msg)
	{
		return new ResponseResultVo(status, msg);
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}
}
