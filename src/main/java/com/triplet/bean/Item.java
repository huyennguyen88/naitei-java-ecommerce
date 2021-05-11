package com.triplet.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.triplet.model.OrderItem;
import com.triplet.model.Product;
import com.triplet.utils.FormatUtils;

public class Item {
	private int id;
	private String name;
	private BigDecimal price;
	private String formatPrice;
	private int quantity;
	private String image;
	private BigDecimal sumPrice;
	private String formatSumPrice;
	private Product product;

	public Item(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
		this.sumPrice = this.price;
		this.image = product.getImage().split("/")[0];
		this.quantity = 1;
		this.product = product;
		
		FormatUtils formatUtils = new FormatUtils();
		this.formatPrice = formatUtils.formatCurrency(this.price);
		this.formatSumPrice = formatUtils.formatCurrency(this.sumPrice);
	}

	public Item(OrderItem orderItem) {
		this.product = orderItem.getProduct();
		this.id = product.getId();
		this.name = product.getName();
		this.price = product.getPrice();
		this.sumPrice = orderItem.getPrice_total();
		this.image = product.getImage().split("/")[0];
		this.quantity = orderItem.getQuantity();
		
		FormatUtils formatUtils = new FormatUtils();
		this.formatPrice = formatUtils.formatCurrency(this.price);
		this.formatSumPrice = formatUtils.formatCurrency(this.sumPrice);
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getFormatPrice() {
		return formatPrice;
	}

	public void setFormatPrice(String formatPrice) {
		this.formatPrice = formatPrice;
	}

	public BigDecimal getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(BigDecimal sumPrice) {
		this.sumPrice = sumPrice;
	}

	public String getFormatSumPrice() {
		return formatSumPrice;
	}

	public void setFormatSumPrice() {
		FormatUtils formatUtils = new FormatUtils();
		this.formatSumPrice = formatUtils.formatCurrency(this.sumPrice);
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public OrderItem toOrderItem() {
		OrderItem orderItem = new OrderItem();
		orderItem.setPrice_unit(this.price);
		orderItem.setQuantity(this.quantity);
		orderItem.setPrice_total(this.sumPrice);
		orderItem.setProduct(this.product);
		return orderItem;
	}
	
	public static List<Item> toItems(List<OrderItem> orderItems){
		List<Item> items = new ArrayList<Item>();
		for(OrderItem i : orderItems) {
			items.add(new Item(i));
		}
		return items;
	}
}
