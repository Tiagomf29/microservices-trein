package com.msstock.domain.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.msstock.domain.model.Product;
import com.msstock.domain.repository.ProductRepository;

@Controller
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	
	@MutationMapping
	public Product saveProduct(@Argument int code,@Argument String name) {
		Product p = new Product();
		p.setCode(code);
		p.setName(name);
		return productRepository.save(p);
	}
	
	@MutationMapping
	public Product updateProduct(@Argument int id, @Argument int code, @Argument String name) {
		
		Optional<Product> product = productRepository.findById(id);
		if(product.isPresent()) {
			product.get().setCode(code);
			product.get().setName(name);
			productRepository.save(product.get());
		}
		
		return product.get();
	}
	
	@MutationMapping
	public Boolean deleteProduct(@Argument int id) {
		productRepository.deleteById(id);
		return true;
	}
	
	
	@QueryMapping
	public List<Product> products(){
		return productRepository.findAll();
	}
	
	@QueryMapping
	public Product productById(@Argument int id){
		return productRepository.findById(id).get();
	}
	

	
	
}
