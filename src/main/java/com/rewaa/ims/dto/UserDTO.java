package com.rewaa.ims.dto;

import javax.validation.constraints.NotEmpty;

public class UserDTO implements IModel
{

	private static final long	serialVersionUID	= 2934828531973115557L;
	private int					id;
	@NotEmpty(message = "User loginid should not be empty")
	private String				loginId;
	@NotEmpty(message = "User password should not be empty")
	private String				password;
	private String				userName;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getLoginId()
	{
		return loginId;
	}

	public void setLoginId(String loginId)
	{
		this.loginId = loginId;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

}
