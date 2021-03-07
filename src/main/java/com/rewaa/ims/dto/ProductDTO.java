package com.rewaa.ims.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ProductDTO implements IModel
{
	private static final long	serialVersionUID	= 6449887159063498206L;
	private int					id;
//	@NotEmpty(message = "product name should not be empty")
	private String				name;
//	@Min(value = 0, message = "Value can not be in negative")
	private float				price;
	private String				description;
//	@NotEmpty(message = "Vendor name should not be empty")
	private String				vendor;

	public ProductDTO()
	{
	}

	public ProductDTO(int id, String name, float price)
	{
		this.id = id;
		this.name = name;
		this.price = price;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public float getPrice()
	{
		return price;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getVendor()
	{
		return vendor;
	}

	public void setVendor(String vendor)
	{
		this.vendor = vendor;
	}

}
