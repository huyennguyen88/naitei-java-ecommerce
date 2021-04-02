package com.triplet.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.triplet.model.User;
import com.triplet.service.UserService;

public class UserServiceImpl extends BaseServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Override
	public List<User> loadUsers() {
		return getUserDAO().loadUsers();
	}

	@Override
	public User findById(Serializable key) {
		return getUserDAO().findById(key);
	}

	@Override
	public User saveOrUpdate(User entity) {
		try {
			return getUserDAO().saveOrUpdate(entity);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

	@Override
	public boolean delete(User entity) {
		try {
			getUserDAO().delete(entity);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
}
