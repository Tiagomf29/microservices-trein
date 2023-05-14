package com.msproduct.projection;

import org.springframework.data.rest.core.config.Projection;

import com.msproduct.domain.model.Product;
import com.msproduct.domain.model.ProductType;

@Projection(name = "ProductDto", types = {Product.class})
public interface ProductProjection {

	int getId();
	String getName();
	ProductType productType();
	
}
