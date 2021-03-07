package com.rewaa.ims.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.rewaa.ims.dto.IModel;

public interface DBManagerInterface
{

	int insert(String queryName, IModel modelObject) throws Exception;

	int update(String queryName, IModel modelObject) throws Exception;

	void deleteObjectById(String queryName, int id) throws Exception;

	Integer getUniqueCount(String queryName, Object modelObject) throws Exception;

	Object getResultAsObjectById(String queryName, int id) throws Exception;

	Object getResultAsObjectByLoginId(String queryName, String LoginId) throws Exception;

	<T> List<T> getResultAsList(String queryName, T parameterObject) throws Exception;

	<T> List<T> getResultList(String queryName, Object parameterObject) throws Exception;

	SqlSession getSession();

}
