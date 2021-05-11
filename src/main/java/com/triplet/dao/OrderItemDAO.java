package com.triplet.dao;

import java.util.List;

import com.triplet.model.OrderItem;

public interface OrderItemDAO extends BaseDAO<Integer, OrderItem> {
	
	List<OrderItem> loadByOrderId(int orderId);
}
