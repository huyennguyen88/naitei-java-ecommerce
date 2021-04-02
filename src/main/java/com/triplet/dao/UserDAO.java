package com.triplet.dao;

import java.util.List;

import com.triplet.model.User;

public interface UserDAO extends BaseDAO<Integer, User> {

	List<User> loadUsers();

}
