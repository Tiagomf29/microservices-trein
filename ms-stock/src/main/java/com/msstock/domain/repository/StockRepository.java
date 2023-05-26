package com.msstock.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.msstock.domain.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer>{

	
	@Query("select a from Stock a join fetch a.product where a.product.name like :name")
	List<Stock> findByProductName(String name);
	
	
}
