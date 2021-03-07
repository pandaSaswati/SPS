package com.rewaa.ims.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CommonResponse implements Serializable
{
	private static final long	serialVersionUID	= 1L;

	private String				status;
	private String				errorcode;
	private String				internalErrorMessage;
	private List<Object>		responseObject		= new ArrayList<Object>();

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getErrorcode()
	{
		return errorcode;
	}

	public void setErrorcode(String errorcode)
	{
		this.errorcode = errorcode;
	}

	public List<Object> getResponseObject()
	{
		return responseObject;
	}

	public void setResponseObject(Object responceObject)
	{
		this.responseObject.add(responceObject);
	}

	public String getInternalErrorMessage()
	{
		return internalErrorMessage;
	}

	public void setInternalErrorMessage(String internalErrorMessage)
	{
		this.internalErrorMessage = internalErrorMessage;
	}

	@Override
	public String toString()
	{
		return "CommonResponse [status=" + status + ", errorcode=" + errorcode + ", internalErrorMessage=" + internalErrorMessage + ", responseObject="
				+ responseObject + "]";
	}

}
