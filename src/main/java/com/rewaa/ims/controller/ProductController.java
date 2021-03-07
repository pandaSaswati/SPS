package com.rewaa.ims.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rewaa.ims.dto.ProductDTO;
import com.rewaa.ims.service.ProductService;
import com.rewaa.ims.utils.CommonResponse;
import com.rewaa.ims.utils.URIConstants;

@RestController
public class ProductController
{
	private static final Logger	LOGGER	= LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService		service;

	@GetMapping(path = URIConstants.GET_ALL_PRODUCT_LIST)
	public CommonResponse list()
	{
		LOGGER.info("Getting product list");
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setResponseObject(service.listAll());
		LOGGER.info("Getting of product list completed");
		return commonResponse;
	}

	@GetMapping(path = URIConstants.GET_PRODUCT_BY_ID)
	public CommonResponse getProductById(@PathVariable Integer productId)
	{
		LOGGER.info("Getting product dtailsBy id: {}", productId);
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setResponseObject(service.get(productId));
		LOGGER.info("Getting of product list completed");
		return commonResponse;
	}

	@PostMapping(path = URIConstants.INSERT_PRODUCT)
	public CommonResponse add(@RequestBody(required = true) ProductDTO product)
	{
		LOGGER.info("Inserting product dtails");
		CommonResponse commonResponse = new CommonResponse();
		product.setId(0);
		service.save(product);
		commonResponse.setResponseObject(product);
		LOGGER.info("Product details inserted");
		return commonResponse;
	}

	@PatchMapping(path = URIConstants.UPDATE_PRODUCT)
	public CommonResponse update(@RequestBody(required = true) ProductDTO product, @PathVariable Integer productId)
	{
		LOGGER.info("Updating product dtails by id: {}", productId);
		service.save(product, productId);
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setResponseObject("Your product saved successfully by:  " + productId);
		LOGGER.info("Updating successfully dtails by id: {}", productId);
		return commonResponse;
	}

	@DeleteMapping(path = URIConstants.DELETE_PRODUCT)
	public CommonResponse delete(@PathVariable Integer productId)
	{
		LOGGER.info("Deleting product dtails by id: {}", productId);
		service.delete(productId);
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setResponseObject("Product deleted by id: " + productId);
		LOGGER.info("Deleted product dtails by id: {}", productId);
		return commonResponse;
	}

	@DeleteMapping(path = URIConstants.DELETE_PRODUCT_BY_IDS)
	public CommonResponse delete(@RequestBody List<Integer> productIds)
	{
		LOGGER.info("Deleting product dtails by ids: {}", productIds);
		service.delete(productIds);
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setResponseObject("Product deleted by id: " + productIds.stream().map(String::valueOf).collect(Collectors.joining(",")));
		LOGGER.info("Deleted product dtails by ids: {}", productIds);
		return commonResponse;
	}
}
