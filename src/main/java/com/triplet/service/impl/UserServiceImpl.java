package com.triplet.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.triplet.bean.UserInfo;
import com.triplet.model.Role;
import com.triplet.model.User;
import com.triplet.service.UserService;

public class UserServiceImpl extends BaseServiceImpl implements UserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<User> loadUsers() {
		return getUserDAO().loadUsers();
	}

	@Override
	public User findById(Serializable key) {
		return getUserDAO().findById(key);
	}

	@Override
	public User findByUsername(String username) {
		return getUserDAO().findByUsername(username);
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

	@Override
	public boolean checkEmailExist(String email) {
		try {
			// true: exist
			return getUserDAO().checkEmailExist(email);
		} catch (Exception e) {
			logger.error(e);
		}
		return false;
	}

	@Override
	public boolean checkUsernameExist(String username) {
		try {
			// true: exist
			return getUserDAO().checkUsernameExist(username);
		} catch (Exception e) {
			logger.error(e);
		}
		return false;
	}

	@Override
	public boolean createUser(User user) {

		try {
			if (checkEmailExist(user.getEmail()) || checkUsernameExist(user.getUsername()))
				return false;
			Role role = new Role();
			role.setId(2);
			role.setCode("USER");
			List<Role> roles = new ArrayList<>();
			roles.add(role);
			user.setRoles(roles);
			if(user.getPassword()!=null) {
				user.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			getUserDAO().saveOrUpdate(user);
			return true;
		} catch (Exception ex) {
			logger.error("Error in saveUserAfterRegister: " + ex.getMessage());
			throw ex;
		}
	}

	@Override
	public boolean saveBatch(List<User> users) {
		try {
			return getUserDAO().saveBatch(users);
		} catch (Exception e) {
			logger.error("Error in save batch of user: " + e.getMessage());
			throw e;
		}
	}

	public List<User> convertToUsers(List<UserInfo> listUserInfo) {
		List<User> users = new ArrayList<User>();
		for (UserInfo userInfo : listUserInfo) {
			User user = userInfo.convertToUser();

			Role role = new Role();
			role.setId(2);
			role.setCode("USER");
			List<Role> roles = new ArrayList<>();
			roles.add(role);
			user.setRoles(roles);
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			users.add(user);
		}
		return users;
	}
}
