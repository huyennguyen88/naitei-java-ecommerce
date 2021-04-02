package com.triplet.service;

import java.util.List;

import com.triplet.model.User;

public interface UserService extends BaseService<Integer, User> {

	List<User> loadUsers();

}
