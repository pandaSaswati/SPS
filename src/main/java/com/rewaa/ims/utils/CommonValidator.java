package com.rewaa.ims.utils;

import java.util.Collection;

public interface CommonValidator
{

	public static boolean isCollectionsIsNullOrEmpty(Collection<?> collection)
	{
		return collection == null || collection.isEmpty();
	}

	public static boolean isObjectIsNull(Object classObject)
	{
		return classObject == null;
	}

	public static boolean isStringIsNullOrEmpty(String str)
	{
		return str == null || str.trim().isEmpty();
	}

}
