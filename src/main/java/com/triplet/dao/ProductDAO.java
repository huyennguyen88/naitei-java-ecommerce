package com.triplet.dao;

import java.util.List;

import com.triplet.model.Product;

public interface ProductDAO extends BaseDAO<Integer, Product> {

	List<Product> loadProductsByCategory(int categoryId);
	
	List<String> getListNamesOfProduct(String term);
	
	List<Product> findByName(String name);
}