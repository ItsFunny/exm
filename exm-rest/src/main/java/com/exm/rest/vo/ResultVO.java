package com.exm.rest.vo;

public class ResultVO
{

	private Integer status;
	private String msg;
	private Object data;

	public ResultVO(Integer status, String msg, Object data)
	{
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public ResultVO(Integer status, String msg)
	{
		this.status = status;
		this.msg = msg;

	}

	public static ResultVO build(Integer status, String msg)
	{
		return new ResultVO(status, msg);
	}

	public static ResultVO build(Integer status, String msg, Object data)
	{
		return new ResultVO(status, msg, data);
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
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
