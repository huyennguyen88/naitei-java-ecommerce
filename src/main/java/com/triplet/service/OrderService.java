package com.triplet.service;

import java.util.List;

import com.triplet.bean.OrderInfo;
import com.triplet.model.Order;

public interface OrderService extends BaseService<Integer, Order> {
	List<Order> loadByUserId(int userId);

	boolean createOrder(OrderInfo orderInfo );
}
