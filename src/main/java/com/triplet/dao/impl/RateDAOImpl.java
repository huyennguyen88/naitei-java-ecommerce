package com.triplet.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.triplet.dao.GenericDAO;
import com.triplet.dao.RateDAO;
import com.triplet.model.Rate;

public class RateDAOImpl extends GenericDAO<Integer, Rate> implements RateDAO {
	
	public RateDAOImpl() {
		super(Rate.class);
	}

	public RateDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rate> loadRatings(Integer productId) {
		return getSession().createQuery("from Rate where product.id=:productId").setParameter("productId", productId)
				.getResultList();
	}
}