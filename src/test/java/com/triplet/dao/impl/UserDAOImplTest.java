package com.triplet.dao.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import com.triplet.model.User;
import factories.model.UserFake;

class UserDAOImplTest {

	@Mock
	private static SessionFactory sessionFactory;

	@Mock
	private Session session;

	@Mock
	private Query<User> query;

	@InjectMocks
	private UserDAOImpl userDAO;

	@BeforeEach
	private void setup() {
		MockitoAnnotations.openMocks(this);
		ReflectionTestUtils.setField(userDAO, "sessionFactory", sessionFactory);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
	}

	@Test
	public void test_loadUsers_dao_when_load_users_success() {
		UserFake userFake = new UserFake();
		List<User> users = userFake.getUsers();

		when(session.createQuery("from User")).thenReturn(query);
		when(query.getResultList()).thenReturn(users);

		assertEquals(users, userDAO.loadUsers());
	}

	@Test
	public void test_loadUsers_dao_when_load_users_fail() {
		when(session.createQuery("from User")).thenReturn(query);
		when(query.getResultList()).thenReturn(null);

		assertNull(userDAO.loadUsers());
	}

	@Test
	public void test_findByUsername_dao_when_user_exist() {
		UserFake userFake = new UserFake();
		User user = userFake.getUser();

		when(session.createQuery("from User where username=: username")).thenReturn(query);
		when(query.setParameter("username", "ngocanh")).thenReturn(query);
		when(query.uniqueResult()).thenReturn(user);

		assertEquals(user, userDAO.findByUsername("ngocanh"));
	}

	@Test
	public void test_findByUsername_dao_when_user_not_exist() {
		when(session.createQuery("from User where username=: username")).thenReturn(query);
		when(query.setParameter("username", "ngocanh")).thenReturn(query);
		when(query.uniqueResult()).thenReturn(null);

		assertNull(userDAO.findByUsername("ngocanh"));
	}

	@Test
	public void test_saveOrUpdate() {
		UserFake userFake = new UserFake();
		User user = userFake.getUser();

		doNothing().when(session).saveOrUpdate(user);

		assertEquals(user, userDAO.saveOrUpdate(user));
		verify(session, times(1)).saveOrUpdate(user);
	}

	@Test
	public void test_delete() {
		UserFake userFake = new UserFake();
		User user = userFake.getUser();

		doNothing().when(session).delete(user);

		userDAO.delete(user);
		verify(session, times(1)).delete(user);
	}
}
