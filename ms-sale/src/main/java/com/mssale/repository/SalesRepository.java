package com.mssale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mssale.model.Sales;

public interface SalesRepository extends JpaRepository<Sales, Integer>{

}
