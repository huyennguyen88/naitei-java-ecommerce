package com.triplet.bean;

public class Ship {
	private String address;
	private String fullname;
	private String phone;
	
	public Ship() {
		
	}
	
	public Ship(String fullname, String address, String phone) {
		this.fullname = fullname;
		this.address = address;
		this.phone = phone;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}