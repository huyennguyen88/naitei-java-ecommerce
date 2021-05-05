package com.triplet.controller.web;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.triplet.bean.UserInfo;
import com.triplet.model.User;
import com.triplet.service.UserService;
import com.triplet.service.impl.MyUser;

import factories.authentication.FakeMyUser;
import factories.model.UserFake;
import io.florianlopes.spring.test.web.servlet.request.MockMvcRequestBuilderUtils;

@RunWith(MockitoJUnitRunner.class)
class UserControllerTest {

	private MockMvc mockMvc;
	
	@Mock
	private UserService userService;
	
	@Mock
	private Authentication authentication;
	
	@Mock
	private SecurityContext securityContext;
	
	@InjectMocks
	private UserController userController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(this.userController).build();
		when(securityContext.getAuthentication()).thenReturn(authentication);
		SecurityContextHolder.setContext(securityContext);
	}

	@Test
	public void test_show_action_when_login_user_success() throws Exception {
		FakeMyUser fakeMyUser = new FakeMyUser();
		MyUser currentUser = fakeMyUser.getCurrentUser();
		UserFake userFake = new UserFake();
		User user = userFake.getUser();

		when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(currentUser);
		when(userService.findById(1)).thenReturn(user);
		
		mockMvc.perform(get("/users/{id}", 1))
				.andExpect(status().isOk())
				.andExpect(model().size(1))
				.andExpect(model().attributeExists("userInfo"))
				.andExpect(model().attribute("userInfo", hasProperty("id", is(1))))
				.andExpect(forwardedUrl("views/web/users/user"));
       
		verify(userService, times(1)).findById(1);
		verifyNoMoreInteractions(userService);
	}
	
	@Test
	public void test_show_action_when_different_user_access() throws Exception {
		FakeMyUser fakeMyUser = new FakeMyUser();
		MyUser currentUser = fakeMyUser.getCurrentUser();
		
		when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(currentUser);
		
		mockMvc.perform(get("/users/{id}", 2))
				.andExpect(status().isFound())
				.andExpect(model().attributeDoesNotExist("userInfo"))
				.andExpect(redirectedUrl("/login?accessDenied"));
		verifyNoInteractions(userService);
	}
	
	@Test
	public void test_show_when_user_logout() throws Exception {
		when(SecurityContextHolder.getContext().getAuthentication().getPrincipal()).thenReturn(null);
		mockMvc.perform(get("/users/{id}", 1))
				.andExpect(status().isFound())
				.andExpect(model().attributeDoesNotExist("userInfo"))
				.andExpect(redirectedUrl("/login?accessDenied"));
		verifyNoInteractions(userService);
	}
	
	@Test
	public void test_saveOrUpdate_action_success() throws Exception {
		UserFake userFake = new UserFake();
		User user = userFake.getUser();
		UserInfo userInfo = userFake.getUserInfo();
		
		when(userService.findById(1)).thenReturn(user);
		when(userService.saveOrUpdate(user)).thenReturn(user);
		
		mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/users/1/update",userInfo))
				.andExpect(status().isFound())
				.andExpect(model().hasNoErrors())
				.andExpect(redirectedUrl("/users/1"))
				.andExpect(flash().attribute("css", "success"));
		
		verify(userService,times(1)).findById(1);
		verify(userService,times(1)).saveOrUpdate(user);
		verifyNoMoreInteractions(userService);
	}
	
	@Test
	public void test_saveOrUpdate_action_fail() throws Exception {
		UserFake userFake = new UserFake();
		User user = userFake.getUser();
		UserInfo userInfo = userFake.getUserInfo();
		
		when(userService.findById(1)).thenReturn(user);
		when(userService.saveOrUpdate(user)).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilderUtils.postForm("/users/1/update",userInfo))
				.andExpect(status().isFound())
				.andExpect(model().hasNoErrors())
				.andExpect(redirectedUrl("/users/1"))
				.andExpect(flash().attribute("css", "error"));
		
		verify(userService,times(1)).findById(1);
		verify(userService,times(1)).saveOrUpdate(user);
		verifyNoMoreInteractions(userService);
	}	
}