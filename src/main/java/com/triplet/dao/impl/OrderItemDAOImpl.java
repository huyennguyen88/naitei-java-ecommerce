package com.triplet.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;

import com.triplet.dao.GenericDAO;
import com.triplet.dao.OrderItemDAO;
import com.triplet.model.OrderItem;

public class OrderItemDAOImpl extends GenericDAO<Integer, OrderItem> implements OrderItemDAO {

	public OrderItemDAOImpl() {
		super(OrderItem.class);
	}

	public OrderItemDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderItem> loadByOrderId(int orderId) {
		return getSession().createQuery("from OrderItem where order_id=: orderId")
				.setParameter("orderId", orderId).getResultList();
	}
}
