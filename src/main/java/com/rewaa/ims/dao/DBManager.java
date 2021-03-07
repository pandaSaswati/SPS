package com.rewaa.ims.dao;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rewaa.ims.dto.IModel;

@Component
public class DBManager implements DBManagerInterface
{
	private static final Logger	LOGGER	= LoggerFactory.getLogger(DBManager.class);
	protected SqlSessionFactory	sqlSessionFactory;

	private DBProperties		dbProperties;

	@Autowired
	public DBManager(DBProperties dbProperties)
	{
		this.dbProperties = dbProperties;
	}

	@PostConstruct
	private void postCunstruct() throws IOException
	{
		init();
	}

	private void init() throws IOException
	{
		Reader reader = Resources.getResourceAsReader("dao/MybatisConfig.xml");
		Properties dbProperties = getDBConnProperties();
		this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader, dbProperties);
		LOGGER.debug("Session factory created..");

	}

	private Properties getDBConnProperties()
	{
		Properties properties = new Properties();
		properties.put("user", dbProperties.getUser());
		properties.put("password", dbProperties.getPassword());
		properties.put("driver", dbProperties.getDriver());
		properties.put("url", dbProperties.getUrl());
		properties.put("maxActive", "5");
		properties.put("maxIdle", "1");
		properties.put("minIdle", "1");
		properties.put("maxWaitTimeInMills", "10000");
		return properties;
	}

	/**
	 * * To add a new IModel Object in DB, returns int with ID of newly inserted row
	 * 
	 * @param modelObject
	 * @param queryName
	 * @return
	 * @throws SQLException
	 */
	public int insert(String queryName, IModel modelObject) throws Exception
	{
		Integer pkId = null;
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			pkId = sqlSession.insert(queryName, modelObject);
			sqlSession.commit();
		}
		catch (Exception e)
		{
			if (sqlSession != null)
				sqlSession.rollback();
			throw e;
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
		return pkId;
	}

	/**
	 * @param modelObject
	 * @param queryName
	 * @throws SQLException
	 */
	public int update(String queryName, IModel modelObject) throws Exception
	{
		int update = 0;
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			update = sqlSession.update(queryName, modelObject);
			sqlSession.commit();
		}
		catch (Exception e)
		{
			if (sqlSession != null)
				sqlSession.rollback();
			throw e;
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
		return update;
	}

	/**
	 * @param modelObject
	 * @param queryName
	 * @throws SQLException
	 */
	public void deleteObjectById(String queryName, int id) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			sqlSession.delete(queryName, id);
			sqlSession.commit();
		}
		catch (Exception e)
		{
			if (sqlSession != null)
				sqlSession.rollback();
			throw e;
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	/**
	 * @param modelObject
	 * @param queryName
	 * @return
	 * @throws SQLException
	 */
	public Integer getUniqueCount(String queryName, Object modelObject) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			return (Integer) sqlSession.selectOne(queryName, modelObject);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	/**
	 * @param queryName
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Object getResultAsObjectById(String queryName, int id) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			return (Object) sqlSession.selectOne(queryName, id);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	/**
	 * @param queryName
	 * @param LoginId
	 * @return
	 * @throws Exception
	 */
	public Object getResultAsObjectByLoginId(String queryName, String LoginId) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			return (Object) sqlSession.selectOne(queryName, LoginId);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	/**
	 * @param <T>
	 * @param queryName
	 * @param parameterObject
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> getResultAsList(String queryName, T parameterObject) throws Exception
	{
		return getResultAsList(queryName, parameterObject, 0, 0);
	}

	/**
	 * @param <T>
	 * @param queryName
	 * @param parameterObject
	 * @param skipResults
	 * @param maxResults
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> getResultAsList(String queryName, T parameterObject, int skipResults, int maxResults) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			if (skipResults < 1)
			{
				skipResults = RowBounds.NO_ROW_OFFSET;
			}
			if (maxResults < 1)
			{
				maxResults = RowBounds.NO_ROW_LIMIT;
			}

			RowBounds bounds = new RowBounds(skipResults, maxResults);
			return sqlSession.selectList(queryName, parameterObject, bounds);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

	/**
	 * @param queryName
	 * @param parametersObject
	 * @return
	 * @throws SQLException
	 */
	public <T> List<T> getResultList(String queryName, Object parameterObject) throws Exception
	{
		return getResultList(queryName, parameterObject, 0, 0);
	}

	/**
	 * @param queryName
	 * @param parameterObject
	 * @param skipResults
	 * @param maxResults
	 * @return
	 * @throws SQLException
	 */
	public <T> List<T> getResultList(String queryName, Object parameterObject, int skipResults, int maxResults) throws Exception
	{
		List<T> resultList = new ArrayList<T>();
		long startTime = System.currentTimeMillis();
		LOGGER.debug("Executing Ibatis Query : {} param: {}", queryName, parameterObject);
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			if (sqlSession == null)
			{
				LOGGER.error("sql Session is null");
				return resultList;
			}
			if (skipResults < 1)
				skipResults = RowBounds.NO_ROW_OFFSET;
			if (maxResults < 1)
				maxResults = RowBounds.NO_ROW_LIMIT;

			RowBounds bounds = new RowBounds(skipResults, maxResults);

			resultList = sqlSession.selectList(queryName, parameterObject, bounds);
		}
		catch (Exception e)
		{
			LOGGER.error("Exception ", e);
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
		LOGGER.debug("Ibatis Query : {} executed in: {} ms", queryName, (System.currentTimeMillis() - startTime));
		return resultList;
	}

	/**
	 * @return
	 */
	public SqlSession getSession()
	{
		return sqlSessionFactory.openSession();
	}

	public <T> void getResultByHandler(String queryName, T parameterObject, ResultHandler handler) throws Exception
	{
		getResultByHandler(queryName, parameterObject, handler, 0, 0);
	}

	public <T> void getResultByHandler(String queryName, T parameterObject, ResultHandler handler, int skipResults, int maxResults) throws Exception
	{
		SqlSession sqlSession = null;
		try
		{
			sqlSession = sqlSessionFactory.openSession();
			if (skipResults < 1)
				skipResults = RowBounds.NO_ROW_OFFSET;
			if (maxResults < 1)
				maxResults = RowBounds.NO_ROW_LIMIT;

			RowBounds bounds = new RowBounds(skipResults, maxResults);
			sqlSession.select(queryName, parameterObject, bounds, handler);
		}
		catch (Exception e)
		{
			throw e;
		}
		finally
		{
			if (sqlSession != null)
			{
				sqlSession.close();
			}
		}
	}

}
