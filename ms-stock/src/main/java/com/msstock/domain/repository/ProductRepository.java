package com.msstock.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msstock.domain.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
