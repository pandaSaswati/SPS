package com.rewaa.ims.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rewaa.ims.dto.ProductDTO;

public interface ProductRepository //extends JpaRepository<ProductDTO, Integer>
{
	ProductDTO getProductByName(String name);
}
