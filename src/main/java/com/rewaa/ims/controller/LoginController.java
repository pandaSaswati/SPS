package com.rewaa.ims.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rewaa.ims.dto.UserDTO;
import com.rewaa.ims.service.UserService;
import com.rewaa.ims.utils.CommonResponse;
import com.rewaa.ims.utils.URIConstants;

@RestController
public class LoginController
{
	private static final Logger	LOGGER	= LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private UserService			userservice;

	@PostMapping(path = URIConstants.LOGIN)
	public CommonResponse doLogin(HttpServletResponse response, HttpServletRequest request, @RequestBody(required = true) UserDTO user)
	{
		LOGGER.info("LOGIN: login validation with loginid: {}", user.getLoginId());
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setResponseObject(userservice.getUser(user));
		LOGGER.info("LOGIN: login validation successfully with loginid: {}", user.getLoginId());
		return commonResponse;
	}
}
