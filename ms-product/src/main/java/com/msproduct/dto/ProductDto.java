package com.msproduct.dto;

import com.msproduct.domain.model.ProductType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ProductDto {

	private int id;
	private String name;
	private ProductType productType;
	
}
