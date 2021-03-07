package com.rewaa.ims.exeptionhandler;

public class InvalidInputException extends RuntimeException
{
	private static final long serialVersionUID = -7784056039021399470L;

	public InvalidInputException(String msg)
	{
		super(msg);
	}

}
