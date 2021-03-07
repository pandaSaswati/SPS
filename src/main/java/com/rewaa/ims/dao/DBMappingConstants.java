package com.rewaa.ims.dao;

public interface DBMappingConstants
{
	String	USER_DETAILS					= "User.getAll";
	String	USER_DETAILS_BY_LOGIN_ID		= "User.getUserByLogInId";

	// Product
	String	GET_PRODUCT_LIST				= "Product.getAll";
	String	GET_PRODUCT_BY_ID				= "Product.getProductById";
	String	INSERT_PRODUCT					= "Product.insert";
	String	UPDATE_PRODUCT					= "Product.update";
	String	GET_PRODUCT_COUNT_BY_ID_NAME	= "Product.getCountByName";
	String	DELETE_PRODUCT_BY_ID			= "Product.delete";
}
