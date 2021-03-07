package com.rewaa.ims.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rewaa.ims.dao.ProductDAO;
import com.rewaa.ims.dto.ProductDTO;
import com.rewaa.ims.exeptionhandler.ElementNotFoundException;
import com.rewaa.ims.exeptionhandler.InvalidInputException;
import com.rewaa.ims.utils.CommonValidator;

@Service
@Transactional
public class ProductService
{
	private static final Logger	LOGGER	= LoggerFactory.getLogger(ProductService.class);

	// @Autowired
	// private ProductRepository repo;

	@Autowired
	private ProductDAO			productDAO;

	public List<ProductDTO> listAll()
	{
		// return repo.findAll();
		return productDAO.getProductList();
	}

	public void save(ProductDTO product, Integer id)
	{
		if (null == id)
		{
			throw new InvalidInputException("product id can not be null");
		}
		product.setId(id);
		save(product);
	}

	private void validate(ProductDTO product)
	{
		if (CommonValidator.isStringIsNullOrEmpty(product.getName()))
		{
			throw new InvalidInputException("Product name is required");
		}
		if (CommonValidator.isStringIsNullOrEmpty(product.getVendor()))
		{
			throw new InvalidInputException("Vendor name is required");
		}
		if (product.getPrice() < 0)
		{
			throw new InvalidInputException("Product price can not be negative");
		}

		if (product.getId() > 0)
		{
			get(product.getId());
		}
		int count = productDAO.getCountByName(product);
		if (count > 0)
		{
			throw new InvalidInputException("Provided product name already present. Please check the product list");
		}

	}

	public void save(ProductDTO product)
	{
		// repo.save(product);
		validate(product);
		if (product.getId() > 0)
		{
			productDAO.update(product);
		}
		else
		{
			productDAO.insert(product);
		}
	}

	public ProductDTO get(int id)
	{
		ProductDTO productDTO = productDAO.getProductDetailsById(id);
		if (CommonValidator.isObjectIsNull(productDTO))
		{
			throw new ElementNotFoundException("Product not found for id: " + id);
		}

		return productDTO;
	}

	public void delete(Integer id)
	{
		productDAO.delete(id);
	}

	public void delete(List<Integer> productIds)
	{
		if (CommonValidator.isCollectionsIsNullOrEmpty(productIds))
		{
			throw new InvalidInputException("Product can not be empty for delete product details");
		}

		productDAO.delete(productIds);

	}
}