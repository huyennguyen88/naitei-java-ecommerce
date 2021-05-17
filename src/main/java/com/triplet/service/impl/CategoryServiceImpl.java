package com.triplet.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.triplet.model.Category;
import com.triplet.service.CategoryService;

public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService {

	private static final Logger logger = Logger.getLogger(CategoryServiceImpl.class);

	@Override
	public Category findById(Serializable key) {
		try {
			return getCategoryDAO().findById(key);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Category saveOrUpdate(Category entity) {
		try {
			return getCategoryDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean delete(Category entity) {
		try {
			getCategoryDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<Category> getRoots() {
		try {
			return getCategoryDAO().getRoots();
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public List<Category> getLeafNodes() {
		List<Category> roots = getRoots();
		List<Category> categories = new ArrayList<Category>();
		try {
			for (Category root : roots) {
				List<Category> leaves = root.getAllLeafNodes();
				categories.addAll(leaves);
			}
			return categories;
		} catch (Exception e) {
			logger.error(e);
		}
		return Collections.emptyList();
	}
}
