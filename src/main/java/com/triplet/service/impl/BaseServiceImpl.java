package com.triplet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.triplet.dao.CategoryDAO;
import com.triplet.dao.ProductDAO;
import com.triplet.dao.UserDAO;

public class BaseServiceImpl {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CategoryDAO categoryDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public CategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	
	public static <T> Page<T> findPaginationBase(Pageable pageable, List<T> objects){
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<T> subObjects = new ArrayList<>();
		if (objects.size() >= startItem) {
			int toIndex = Math.min(startItem + pageSize, objects.size());
			subObjects = objects.subList(startItem, toIndex);
		}
		Page<T> objectPage = new PageImpl<T>(subObjects, PageRequest.of(currentPage, pageSize),
				objects.size());
		return objectPage;
	}
}
