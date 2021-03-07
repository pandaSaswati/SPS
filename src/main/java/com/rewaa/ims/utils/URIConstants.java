package com.rewaa.ims.utils;

public interface URIConstants
{
	String	LOGIN					= "/auth/authz/login";
	String	LOGOUT					= "/auth/authz/logout";
	String	GET_ALL_PRODUCT_LIST	= "/auth/authz/product/getall";
	String	GET_PRODUCT_BY_ID		= "/auth/authz/product/{productId}";
	String	UPDATE_PRODUCT			= "/auth/authz/product/{productId}";
	String	INSERT_PRODUCT			= "/auth/authz/product";
	String	DELETE_PRODUCT			= "/auth/authz/product/{productId}";
	String	DELETE_PRODUCT_BY_IDS	= "/auth/authz/product/deleteByids";
}
