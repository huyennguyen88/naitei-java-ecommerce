package com.triplet.service;

import java.util.List;

import com.triplet.bean.OrderInfo;
import com.triplet.model.Order;
import com.triplet.model.Order.Status;

public interface OrderService extends BaseService<Integer, Order> {
	List<Order> loadByUserId(int userId);

	boolean createOrder(OrderInfo orderInfo);

	List<Order> loadOrders();

	List<Order> loadByStatus(Status status);

	boolean updateStatus(int orderId, int action);
		
	String getTotalRevenue();
}
