package com.triplet.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.triplet.model.OrderItem;
import com.triplet.service.OrderItemService;

public class OrderItemServiceImpl extends BaseServiceImpl implements OrderItemService {

	private static final Logger logger = Logger.getLogger(OrderItemServiceImpl.class);

	@Override
	public OrderItem findById(Serializable key) {
		return null;
	}

	@Override
	public OrderItem saveOrUpdate(OrderItem entity) {
		return null;
	}

	@Override
	public boolean delete(OrderItem entity) {
		return false;
	}

	@Override
	public List<OrderItem> loadByOrderId(int orderId) {
		try {
			return getOrderItemDAO().loadByOrderId(orderId);
		} catch (Exception e) {
			logger.error(e);
		}
		return Collections.emptyList();
	}

}
