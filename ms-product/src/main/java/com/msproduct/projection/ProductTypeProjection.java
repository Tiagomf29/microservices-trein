package com.msproduct.projection;

import org.springframework.data.rest.core.config.Projection;

import com.msproduct.domain.model.ProductType;

@Projection(name = "ProducTypetDto", types = {ProductType.class})
public interface ProductTypeProjection {

	int getId();
	String getName();
	
}
