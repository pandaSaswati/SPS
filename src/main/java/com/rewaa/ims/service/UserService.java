package com.rewaa.ims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewaa.ims.dao.UserDAO;
import com.rewaa.ims.dto.UserDTO;
import com.rewaa.ims.exeptionhandler.InvalidInputException;
import com.rewaa.ims.exeptionhandler.UnAuthorizedException;
import com.rewaa.ims.utils.CommonValidator;

@Service
public class UserService
{
	// @Autowired
	// private UserRepository userRepository;

	@Autowired
	private UserDAO userDAO;

	public List<UserDTO> getUserList()
	{
		// return userRepository.findAll();
		return userDAO.getUserList();
	}

	public UserDTO getUser(UserDTO user)
	{
		validation(user);
		UserDTO userDTO = userDAO.findByLoginId(user.getLoginId());
		if (userDTO.getPassword().equals(user.getPassword()))
		{
			return userDTO;
		}
		throw new UnAuthorizedException("You are not authorize to login");
	}

	private void validation(UserDTO user)
	{
		if (CommonValidator.isObjectIsNull(user))
		{
			throw new InvalidInputException("LoginId and password is required for validation");
		}
		if (CommonValidator.isStringIsNullOrEmpty(user.getLoginId()))
		{
			throw new InvalidInputException("Login id should not be empty");
		}
		if (CommonValidator.isStringIsNullOrEmpty(user.getPassword()))
		{
			throw new InvalidInputException("Password should not be empty");
		}
	}

}
