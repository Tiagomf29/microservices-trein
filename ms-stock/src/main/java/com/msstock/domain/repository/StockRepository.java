package com.msstock.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msstock.domain.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer>{

}
