package com.msproduct.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.msproduct.domain.model.ProductType;

@RepositoryRestResource
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer>{

}
