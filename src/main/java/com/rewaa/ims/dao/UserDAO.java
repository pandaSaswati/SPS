package com.rewaa.ims.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rewaa.ims.dto.UserDTO;
import com.rewaa.ims.exeptionhandler.SQLException;

@Repository
public class UserDAO
{

	@Autowired
	private DBManager dbManager;

	public List<UserDTO> getUserList()
	{
		try
		{
			return dbManager.getResultAsList(DBMappingConstants.USER_DETAILS, null);
		}
		catch (Exception e)
		{
			throw new SQLException(e);
		}
	}

	public UserDTO findByLoginId(String loginId)
	{
		try
		{
			return (UserDTO) dbManager.getResultAsObjectByLoginId(DBMappingConstants.USER_DETAILS_BY_LOGIN_ID, loginId);
		}
		catch (Exception e)
		{
			throw new SQLException(e);
		}
	}

}
