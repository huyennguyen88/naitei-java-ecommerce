package com.triplet.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;

import com.triplet.bean.Item;
import com.triplet.bean.OrderInfo;
import com.triplet.model.Order;
import com.triplet.model.Order.Status;
import com.triplet.model.OrderItem;
import com.triplet.model.Product;
import com.triplet.service.OrderService;

public class OrderServiceImpl extends BaseServiceImpl implements OrderService {

	private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

	@Override
	public Order findById(Serializable id) {
		try {
			return getOrderDAO().findById(id);
		} catch (Exception e) {
			logger.error(e);
			return null;
		}
	}

	@Override
	public Order saveOrUpdate(Order entity) {
		try {
			return getOrderDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean delete(Order entity) {
		return false;
	}

	@Override
	public List<Order> loadByUserId(int userId) {
		try {
			return getOrderDAO().loadByUserId(userId);
		} catch (Exception e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	@Override
	public boolean createOrder(OrderInfo orderInfo) {
		try {
			// Create new order
			Order order = new Order();
			order.setPrice_total(orderInfo.getPriceTotal());
			order.setQuantity_total(orderInfo.getItemQuantity());
			order.setReceiver_address(orderInfo.getShip().getAddress());
			order.setReceiver_name(orderInfo.getShip().getFullname());
			order.setReceiver_phone(orderInfo.getShip().getPhone());
			order.setStatus(Status.PENDING);
			order.setUser(orderInfo.getUser());

			Order newOrder = saveOrUpdate(order);

			// Create order's items
			List<Item> items = orderInfo.getItems();
			for (Item i : items) {
				OrderItem orderItem = new OrderItem();
				orderItem.setOrder(newOrder);
				orderItem.setPrice_total(i.getSumPrice());
				orderItem.setPrice_unit(i.getPrice());
				orderItem.setProduct(i.getProduct());
				orderItem.setQuantity(i.getQuantity());

				getOrderItemDAO().saveOrUpdate(orderItem);
			}
			return true;
		} catch (Exception e) {
			logger.error(e);
			throw (e);
		}
	}

	@Override
	public List<Order> loadByStatus(Status status) {
		try {
			return getOrderDAO().loadByStatus(status);
		} catch (Exception e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<Order> loadOrders() {
		try {
			return getOrderDAO().loadOrders();
		} catch (Exception e) {
			logger.error(e);
			return Collections.emptyList();
		}
	}

	@Override
	public boolean updateStatus(int orderId, int statusIndex) {
		try {
			Status newStatus = Status.values()[statusIndex];
			Order order = getOrderDAO().find(orderId, true);
			if (order.getStatus() == Status.PENDING) {
				order.setStatus(newStatus);

				if (newStatus == Status.ACCEPTED) {
					// Update product quantity
					List<OrderItem> items = order.getOrderItems();
					for (OrderItem i : items) {
						// Update product quantity
						Product product = i.getProduct();
						int newQuantity = product.getQuantity() - i.getQuantity();
						product.setQuantity(newQuantity);
						getProductDAO().saveOrUpdate(product);
					}
				}
				saveOrUpdate(order);
				return true;
			}
			return false;
		} catch (Exception e) {
			logger.error(e);
			throw (e);
		}

	}
}
