package com.triplet.service;

import java.util.List;

import com.triplet.model.Category;

public interface CategoryService extends BaseService<Integer, Category> {

	List<Category> getRoots();

	List<Category> getLeafNodes();

}
