package com.triplet.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.triplet.model.Order;
import com.triplet.model.User;
import com.triplet.model.Order.Status;
import com.triplet.utils.FormatUtils;

public class OrderInfo {
	private int id;
	private BigDecimal priceTotal;
	private String formatPriceTotal;
	private int itemQuantity;
	private Status status;
	private Date create_time;
	private List<Item> items;
	private Ship ship;
	private User user;

	public OrderInfo(Order order) {
		this.id = order.getId();
		this.priceTotal = order.getPrice_total();
		this.status = order.getStatus();
		this.create_time = order.getCreate_time();
		this.ship = new Ship(order.getReceiver_name(),order.getReceiver_address(), order.getReceiver_phone());
		FormatUtils formatUtils = new FormatUtils();
		this.formatPriceTotal = formatUtils.formatCurrency(this.priceTotal);
	}
	
	public OrderInfo(Cart cart) {
		this.itemQuantity = cart.getItemQuantity();
		this.priceTotal = cart.getCartPrice();
		this.ship = cart.getShip();
		this.user = cart.getUser();
		this.items = cart.getItems();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(BigDecimal priceTotal) {
		this.priceTotal = priceTotal;
	}

	public String getFormatPriceTotal() {
		return formatPriceTotal;
	}

	public void setFormatPriceTotal(String formatPriceTotal) {
		this.formatPriceTotal = formatPriceTotal;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	public static List<OrderInfo> ordersToOrderInfos(List<Order> orders) {
		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		for (Order order : orders) {
			orderInfos.add(new OrderInfo(order));
		}
		return orderInfos;
	}

}
