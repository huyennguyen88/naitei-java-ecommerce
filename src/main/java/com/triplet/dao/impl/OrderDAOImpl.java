package com.triplet.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.triplet.dao.GenericDAO;
import com.triplet.dao.OrderDAO;
import com.triplet.model.Order;

public class OrderDAOImpl extends GenericDAO<Integer, Order> implements OrderDAO {
	public OrderDAOImpl() {
		super(Order.class);
	}

	public OrderDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> loadByUserId(int userId) {
		return getSession().createQuery("from Order where user_id=:userId").setParameter("userId", userId)
				.getResultList();
	}

}
