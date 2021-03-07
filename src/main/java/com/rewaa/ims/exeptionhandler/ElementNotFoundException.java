package com.rewaa.ims.exeptionhandler;

public class ElementNotFoundException extends RuntimeException
{

	private static final long serialVersionUID = 3615879435995083954L;

	public ElementNotFoundException(String errMsg)
	{
		super(errMsg);
	}
}
