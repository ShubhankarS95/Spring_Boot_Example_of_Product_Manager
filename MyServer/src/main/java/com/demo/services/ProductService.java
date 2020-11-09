package com.demo.services;

import com.demo.entites.Product;

public interface ProductService {
	
	public Iterable<Product> findAll();
	
	public Product find(String id);
	
	public void create(Product product);
	
	public void update(Product product);
	
	public void delete(String id);
}
