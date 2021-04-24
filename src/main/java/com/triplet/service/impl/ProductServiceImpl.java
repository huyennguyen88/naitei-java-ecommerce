package com.triplet.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.triplet.model.Category;
import com.triplet.model.Product;
import com.triplet.service.ProductService;

public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

	@Override
	public List<Product> loadFullProductsByCategory(int categoryId) {
		try {
			List<Product> products = new ArrayList<>();
			Category category = getCategoryDAO().findById(categoryId);
			List<Category> leafNodes = category.getAllLeafNodes();
			for (Category node : leafNodes) {
				products.addAll(getProductDAO().loadProductsByCategory(node.getId()));
			}
			return products;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public Product findById(Serializable key) {
		try {
			return getProductDAO().findById(key);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public Product saveOrUpdate(Product entity) {
		try {
			return getProductDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean delete(Product entity) {
		try {
			getProductDAO().delete(entity);
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public List<String> searchByName(String term) {
		try {
			return getProductDAO().searchByName(term);
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	}

	@Override
	public List<Product> findByName(String name) {
		try {
			return getProductDAO().findByName(name);
		}catch(Exception e) {
			logger.error(e);
		}
		return null;
	}

}
