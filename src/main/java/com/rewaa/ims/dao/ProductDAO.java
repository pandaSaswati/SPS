package com.rewaa.ims.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rewaa.ims.dto.ProductDTO;
import com.rewaa.ims.dto.UserDTO;
import com.rewaa.ims.exeptionhandler.SQLException;

@Repository
public class ProductDAO
{

	@Autowired
	private DBManager dbManager;

	public List<ProductDTO> getProductList()
	{
		try
		{
			return dbManager.getResultAsList(DBMappingConstants.GET_PRODUCT_LIST, null);
		}
		catch (Exception e)
		{
			throw new SQLException(e);
		}
	}

	public ProductDTO getProductDetailsById(int id)
	{
		try
		{
			return (ProductDTO) dbManager.getResultAsObjectById(DBMappingConstants.GET_PRODUCT_BY_ID, id);
		}
		catch (Exception e)
		{
			throw new SQLException(e);
		}
	}

	public void insert(ProductDTO productDTO)
	{
		try
		{
			dbManager.insert(DBMappingConstants.INSERT_PRODUCT, productDTO);
		}
		catch (Exception e)
		{
			throw new SQLException(e);
		}
	}

	public int update(ProductDTO productDTO)
	{
		try
		{
			return dbManager.update(DBMappingConstants.UPDATE_PRODUCT, productDTO);
		}
		catch (Exception e)
		{
			throw new SQLException(e);
		}
	}

	public int getCountByName(ProductDTO productDTO)
	{
		try
		{
			return dbManager.getUniqueCount(DBMappingConstants.GET_PRODUCT_COUNT_BY_ID_NAME, productDTO);
		}
		catch (Exception e)
		{
			throw new SQLException(e);
		}
	}

	public void delete(int id)
	{
		try
		{
			dbManager.deleteObjectById(DBMappingConstants.DELETE_PRODUCT_BY_ID, id);
		}
		catch (Exception e)
		{
			throw new SQLException(e);
		}
	}

	public void delete(List<Integer> productIds)
	{
		final SqlSession sqlSession = dbManager.getSession();
		try
		{
			productIds.forEach((id) -> sqlSession.delete(DBMappingConstants.DELETE_PRODUCT_BY_ID, id));
			sqlSession.commit();
		}
		catch (Exception e)
		{
			if (null != sqlSession)
			{
				sqlSession.rollback();
			}
			throw new SQLException(e);
		}
		finally
		{
			if (null != sqlSession)
			{
				sqlSession.close();
			}
		}

	}
}
