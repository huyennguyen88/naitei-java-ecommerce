package com.triplet.dao.impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
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

	@Override
	public List<String> searchByName(String term) {
		Session session = getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<String> query = builder.createQuery(String.class);
		
		Root<Product> root = query.from(Product.class);
		query.select(root.get("name"));
		String pattern = '%'+term+'%';
		query.where(builder.like(root.get("name"), pattern));
		
		return session.createQuery(query).getResultList();
	}

	@Override
	public List<Product> findByName(String name) {
		Session session = getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		
		Root<Product> root = query.from(Product.class);
		query.select(root);
		String pattern = '%'+name+'%';
		query.where(builder.like(root.get("name"), pattern));
		
		return session.createQuery(query).getResultList();
	}
}
