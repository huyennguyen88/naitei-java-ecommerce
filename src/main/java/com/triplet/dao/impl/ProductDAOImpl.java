package com.triplet.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.triplet.dao.GenericDAO;
import com.triplet.dao.ProductDAO;
import com.triplet.model.Product;

public class ProductDAOImpl extends GenericDAO<Integer, Product> implements ProductDAO {

	public ProductDAOImpl() {
		super(Product.class);
	}

	public ProductDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> loadProductsByCategory(int categoryId) {
		return getSession().createQuery("from Product where category_id=: category_id")
				.setParameter("category_id", categoryId).getResultList();
	}

}
