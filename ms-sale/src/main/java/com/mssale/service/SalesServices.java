package com.mssale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mssale.model.Sales;
import com.mssale.repository.SalesRepository;

@Service
public class SalesServices {

	@Autowired
	private SalesRepository salesRepository;
	
	
	public void insertSale(Sales sales) {
		salesRepository.save(sales);
	}
	
	public void updateSale(Sales sales) {
		salesRepository.save(sales);
	}
	
	public void deleteSale(Integer id) {
		salesRepository.deleteById(id);
	}
	
	public List<Sales> listSales(){
		return salesRepository.findAll();
	}
	
	public Sales listSaleById(Integer id) {
		return salesRepository.findById(id).get();
	}
}
