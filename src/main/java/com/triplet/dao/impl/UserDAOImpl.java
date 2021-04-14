package com.triplet.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
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

	@Override
	public User findByUsername(String username) {
		return (User) getSession().createQuery("from User where username=: username").setParameter("username", username)
				.uniqueResult();
	}

	@Override
	public boolean checkEmailExist(String email) {
		User user = (User) getSession().createQuery("from User where email=: email").setParameter("email", email)
				.uniqueResult();
		if (user != null)
			return true;
		return false;
	}

	@Override
	public boolean checkUsernameExist(String username) {
		User user = (User) getSession().createQuery("from User where username=: username")
				.setParameter("username", username).uniqueResult();
		if (user != null)
			return true;
		return false;
	}

	@Override
	public boolean saveBatch(List<User> users) {
		int batchSize = 10;
		int listSize = users.size();
		Session session = getSession();
		int i;
		for (i = 0; i < listSize; i++) {
			if (i > 0 && i % batchSize == 0) {
				session.flush();
				session.clear();
			}
			session.persist(users.get(i));
		}
		if(i==listSize) {
			logger.info("save batch successfully! i = "+i);
			return true;
		}
		return false;
	}

}
