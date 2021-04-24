package com.triplet.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.triplet.model.Product;

public interface ProductService extends BaseService<Integer, Product>{
	
	List<Product> loadFullProductsByCategory(int categoryId);

	Page<Product> findPagination(Pageable pageable, List<Product> products);

	List<String> getListNamesOfProduct(String term);
	
	List<Product> findByName(String name);
}
