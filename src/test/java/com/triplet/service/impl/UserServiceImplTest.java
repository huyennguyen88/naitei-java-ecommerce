package com.triplet.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.triplet.dao.UserDAO;
import com.triplet.model.User;

class UserServiceImplTest {

	@Mock
	private UserDAO userDAO;

	@InjectMocks
	private UserServiceImpl userService;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void test_loadUsers_service_when_load_users_success() {
		List<User> users = new ArrayList<>();
		when(userDAO.loadUsers()).thenReturn(users);
		assertEquals(users, userService.loadUsers());
		verify(userDAO, times(1)).loadUsers();
		verifyNoMoreInteractions(userDAO);
	}

	@Test
	public void test_loadUsers_service_when_load_users_fail() {
		when(userDAO.loadUsers()).thenReturn(null);
		assertNull(userService.loadUsers());
		verify(userDAO, times(1)).loadUsers();
		verifyNoMoreInteractions(userDAO);
	}

	@Test
	void test_findByUsername_service_when_username_exist() {
		User user = new User();
		String name = "ngocanh";
		when(userDAO.findByUsername(name)).thenReturn(user);
		assertEquals(user, userService.findByUsername(name));
		verify(userDAO, times(1)).findByUsername(name);
		verifyNoMoreInteractions(userDAO);
	}

	@Test
	void test_findByUsername_service_when_username_not_exist() {
		String name = "ngocanh";
		when(userDAO.findByUsername(name)).thenReturn(null);
		assertNull(userService.findByUsername(name));
		verify(userDAO, times(1)).findByUsername(name);
		verifyNoMoreInteractions(userDAO);
	}

	@Test
	public void test_saveOrUpdate_service_when_user_valid() {
		User user = new User();
		when(userDAO.saveOrUpdate(user)).thenReturn(user);
		assertEquals(user,userService.saveOrUpdate(user));
		verify(userDAO, times(1)).saveOrUpdate(user);
		verifyNoMoreInteractions(userDAO);
	}

	@Test
	public void test_saveOrUpdate_service_when_user_invalid() {
		User user = new User();
		when(userDAO.saveOrUpdate(user)).thenReturn(null);
		assertNull(userService.saveOrUpdate(user));
		verify(userDAO, times(1)).saveOrUpdate(user);
		verifyNoMoreInteractions(userDAO);
	}

	@Test
	public void test_delete_service_when_user_exits() {
		User user = new User();
		doNothing().when(userDAO).delete(user);
		assertTrue(userService.delete(user));
		verify(userDAO, times(1)).delete(user);
		verifyNoMoreInteractions(userDAO);
	}
}