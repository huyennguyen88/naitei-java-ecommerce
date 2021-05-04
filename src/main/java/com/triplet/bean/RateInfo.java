package com.triplet.bean;

import com.triplet.model.Rate.Value;

public class RateInfo {
	private int id;
	private String content;
	private Value value;
	private int productId;
	private int userId;

	public RateInfo() {

	}

	public RateInfo(String content, Value value, int productId, int userId) {
		this.content = content;
		this.value = value;
		this.productId = productId;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}