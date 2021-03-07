package com.rewaa.ims.exeptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class UnAuthorizedException extends HttpClientErrorException
{

	private static final long serialVersionUID = 8318316326837502714L;

	public UnAuthorizedException(String statusText)
	{
		super(HttpStatus.UNAUTHORIZED, statusText);
	}

}
