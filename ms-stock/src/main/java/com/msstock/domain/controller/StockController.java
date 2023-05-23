package com.msstock.domain.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.msstock.domain.model.Product;
import com.msstock.domain.model.Stock;
import com.msstock.domain.modelgrahql.ProductImput;
import com.msstock.domain.repository.StockRepository;

@Controller
@CrossOrigin(origins = "*")
public class StockController {

	@Autowired
	private StockRepository stocRepository;
	
	@MutationMapping
	public Stock saveStock(@Argument ProductImput product, @Argument int quantity) {
		Stock stock = new Stock();
		Product p = new Product();
		p.setId(product.getId());
		p.setCode(product.getCode());
		p.setName(product.getName());
		stock.setProduct(p);
		stock.setQuantity(quantity);
		return stocRepository.save(stock);
	}
	
	@MutationMapping
	public Stock updateStock(@Argument int id, @Argument ProductImput product, @Argument int quantity) {
		
		Optional<Stock> stock = stocRepository.findById(id);
		if(stock.isPresent()) {
			Product p = new Product();
			p.setId(product.getId());
			p.setCode(product.getCode());
			p.setName(product.getName());
			stock.get().setProduct(p);
			stock.get().setQuantity(quantity);
			stocRepository.save(stock.get());
		}
		
		return stock.get();
	}
	
	@MutationMapping
	public Boolean deleteStock(@Argument int id) {
		stocRepository.deleteById(id);
		return true;
	}
	
	
	@QueryMapping
	public List<Stock> stocks(){
		return stocRepository.findAll();
	}
	
	@QueryMapping
	public Stock stockById(@Argument int id){
		return stocRepository.findById(id).get();
	}
	
	
}
