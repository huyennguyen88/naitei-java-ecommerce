package com.triplet.bean;

import com.triplet.model.User;

public class UserInfo {

	private int id;
	private String username;
	private String fullname;
	private String email;
	private String password;
	private String confirmPassword;
	private String address;
	private String avatar;
	private String phone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserInfo() {
	}

	public UserInfo(String fullname, String username, String email, String password, String address, String phone,
			String avatar) {
		this.fullname = fullname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phone = phone;
		this.avatar = avatar;
	}

	public User convertToUser() {
		User user = new User();
		user.setFullname(this.getFullname());
		user.setUsername(this.getUsername());
		user.setEmail(this.getEmail());
		user.setPassword(this.getPassword().trim());
		user.setAddress(this.getAddress());
		user.setAvatar(this.getAvatar());
		user.setPhone(this.getPhone());
		return user;
	}
}
