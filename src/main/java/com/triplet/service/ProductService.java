package com.triplet.service;

import java.util.List;

import com.triplet.model.Product;

public interface ProductService extends BaseService<Integer, Product>{
	
	List<Product> loadFullProductsByCategory(int categoryId);
	
	List<String> searchByName(String term);
	
	List<Product> findByName(String name);
}
