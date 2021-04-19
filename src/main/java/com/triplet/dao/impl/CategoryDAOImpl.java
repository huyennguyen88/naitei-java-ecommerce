package com.triplet.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.triplet.dao.CategoryDAO;
import com.triplet.dao.GenericDAO;
import com.triplet.model.Category;

public class CategoryDAOImpl extends GenericDAO<Integer, Category> implements CategoryDAO {

	public CategoryDAOImpl() {
		super(Category.class);
	}

	public CategoryDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getRoots() {
		return getSession().createQuery("from Category where parent_id is null").getResultList();
	}

}
