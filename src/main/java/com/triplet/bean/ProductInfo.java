package com.triplet.bean;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.triplet.model.Product;
import com.triplet.model.Rate;
import com.triplet.utils.FormatUtils;

public class ProductInfo {
	private int id;

	private String name;

	private List<String> descriptions;

	private List<String> images;

	private int quantity;

	private BigDecimal price;
	
	private String formatPrice;

	private double rate_avg;

	private int num_rates;

	public ProductInfo(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.quantity = product.getQuantity();
		this.price = product.getPrice();
		this.rate_avg = calculateRate_avg(product.getRates());
		this.images = Arrays.asList(product.getImage().split("/"));
		this.descriptions = Arrays.asList(product.getDescription().split("/"));
		this.num_rates = product.getRates().size();
		
		FormatUtils formatUtils = new FormatUtils();
		this.formatPrice = formatUtils.formatCurrency(this.price);
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

	public String getFormatPrice() {
		return formatPrice;
	}

	public void setFormatPrice(String formatPrice) {
		this.formatPrice = formatPrice;
	}

	public int getNum_rates() {
		return num_rates;
	}

	public void setNum_rates(int num_rates) {
		this.num_rates = num_rates;
	}

	public double getRate_avg() {
		return rate_avg;
	}

	public void setRate_avg(double rate_avg) {
		this.rate_avg = rate_avg;
	}

	private double calculateRate_avg(List<Rate> rates) {
		double sum = 0;
		for (int i = 0; i < rates.size(); i++) {
			sum = sum + (rates.get(i).getValue().ordinal() + 1);
		}
		double avg = sum / rates.size();
		return Math.round(avg * 100.0) / 100.0;
	}
}
