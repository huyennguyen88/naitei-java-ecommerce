package com.triplet.dao;

import java.util.List;

import com.triplet.model.Order;
import com.triplet.model.Order.Status;

public interface OrderDAO extends BaseDAO<Integer, Order>{
	
	List<Order> loadByUserId(int userId);
	
	List<Order> loadOrders();
	
	List<Order> loadByStatus(Status status);
	
}
