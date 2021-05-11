package com.triplet.dao;

import java.util.List;

import com.triplet.model.Order;

public interface OrderDAO extends BaseDAO<Integer, Order>{
	
	List<Order> loadByUserId(int userId);
	
}
