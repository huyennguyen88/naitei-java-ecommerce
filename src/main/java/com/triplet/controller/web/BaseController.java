package com.triplet.controller.web;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.triplet.service.impl.MyUser;

public abstract class BaseController {
	protected MyUser loadCurrentUser() {
		MyUser user = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}

	protected String handleRedirect(final RedirectAttributes redirectAttributes, String css, String msg,
			String redirectEndpoint) {
		redirectAttributes.addFlashAttribute("css", css);
		redirectAttributes.addFlashAttribute("msg", msg);
		return "redirect:" + redirectEndpoint;
	}
}
