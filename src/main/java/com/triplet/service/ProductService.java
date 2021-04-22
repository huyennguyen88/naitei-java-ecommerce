package com.triplet.service;

import java.util.List;

import com.triplet.model.Product;

public interface ProductService extends BaseService<Integer, Product>{
	
	List<Product> loadFullProductsByCategory(int categoryId);
	
}
