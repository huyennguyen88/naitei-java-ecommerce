package com.triplet.controller.web;

import org.springframework.security.core.context.SecurityContextHolder;
import com.triplet.model.User;

public abstract class BaseController {
	protected User loadCurrentUser() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
}
