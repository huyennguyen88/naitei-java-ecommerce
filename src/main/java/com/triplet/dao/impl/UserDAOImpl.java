package com.triplet.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import com.triplet.dao.GenericDAO;
import com.triplet.dao.UserDAO;
import com.triplet.model.User;

public class UserDAOImpl extends GenericDAO<Integer, User> implements UserDAO {
	Logger logger = Logger.getLogger(UserDAOImpl.class);

	public UserDAOImpl() {
		super(User.class);
	}

	public UserDAOImpl(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> loadUsers() {
		return getSession().createQuery("from User").getResultList();
	}
}
