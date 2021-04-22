package com.triplet.bean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.triplet.model.Product;

public class ProductInfo {
	private int id;

	private String name;

	private List<String> descriptions;

	private List<String> images;

	private int quantity;

	private BigDecimal price;
	
	private double rate_avg;

	public ProductInfo(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.quantity = product.getQuantity();
		this.price = product.getPrice();
		this.rate_avg = product.getRate_avg();
		this.images = Arrays.asList(product.getImage().split("/"));
		this.descriptions = Arrays.asList(product.getDescription().split("/"));
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

	public List<String> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public double getRate_avg() {
		return rate_avg;
	}

	public void setRate_avg(double rate_avg) {
		this.rate_avg = rate_avg;
	}
}
