package com.triplet.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.SessionFactory;

import com.triplet.dao.GenericDAO;
import com.triplet.dao.OrderDAO;
import com.triplet.model.Order;
import com.triplet.model.Order.Status;

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> loadOrders() {
		return getSession().createQuery("from Order").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> loadByStatus(Status status) {
		return (List<Order>) getSession().createQuery("FROM Order where status =: status ")
				.setParameter("status", status).getResultList();
	}

	@Override
	public BigDecimal getTotalRevenue() {
		return (BigDecimal) getSession().createQuery("select SUM(price_total) from Order where status =: status ")
				.setParameter("status", Status.ACCEPTED).uniqueResult();
	}

}
