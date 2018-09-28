package com.exm.model;

public class User
{
	private Long id;

	private Long userId;

	private String password;

	private Integer roleId;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId == null ? null : userId;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password == null ? null : password.trim();
	}

	public Integer getRoleId()
	{
		return roleId;
	}

	public void setRoleId(Integer roleId)
	{
		this.roleId = roleId;
	}
}