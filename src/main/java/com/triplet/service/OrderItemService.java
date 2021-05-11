package com.triplet.service;

import java.util.List;

import com.triplet.model.OrderItem;

public interface OrderItemService extends BaseService<Integer, OrderItem> {
	
	List<OrderItem> loadByOrderId(int orderId);
}
