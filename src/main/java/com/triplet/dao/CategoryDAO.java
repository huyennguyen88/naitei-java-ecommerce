package com.triplet.dao;

import java.util.List;

import com.triplet.model.Category;

public interface CategoryDAO extends BaseDAO<Integer, Category> {

	List<Category> getRoots();

}
