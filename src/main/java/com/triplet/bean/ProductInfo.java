package com.triplet.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.triplet.model.Category;
import com.triplet.model.Product;
import com.triplet.model.Rate;
import com.triplet.utils.FormatUtils;

public class ProductInfo {
	private int id;

	private String name;

	private String description;

	private List<String> images;

	private int quantity;

	private BigDecimal price;

	private String formatPrice;

	private double rate_avg;

	private int num_rates;

	private Category category;

	private int categoryId;

	private Date create_time;

	private Date delete_time;

	private Date update_time;

	public ProductInfo() {
		this.images = new ArrayList<String>();
	}

	public ProductInfo(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.quantity = product.getQuantity();
		this.price = product.getPrice();
		this.rate_avg = calculateRate_avg(product.getRates());
		if (product.getImage() != null) {
			this.images = Arrays.asList(product.getImage().split("/"));
		} else {
			this.images = new ArrayList<String>();
		}
		this.description = product.getDescription();
		this.num_rates = product.getRates().size();
		this.setCategory(product.getCategory());
		this.categoryId = this.category.getId();
		this.create_time = product.getCreate_time();
		this.delete_time = product.getDelete_time();
		this.update_time = product.getUpdate_time();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Date getDelete_time() {
		return delete_time;
	}

	public void setDelete_time(Date delete_time) {
		this.delete_time = delete_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
