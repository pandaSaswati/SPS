package com.rewaa.ims.exeptionhandler;

public class SQLException extends RuntimeException
{
	private static final long serialVersionUID = -5501917132919806694L;

	public SQLException(Exception ex)
	{
		super(ex);
	}
}
