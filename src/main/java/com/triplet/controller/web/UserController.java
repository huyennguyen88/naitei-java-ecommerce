package com.triplet.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.triplet.model.User;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

	@RequestMapping("/{id}")
	public String redering(@PathVariable("id") int id, Model model, HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {

		// Sau khi user login thanh cong se co principal
		// check if user is login
		User user = loadCurrentUser();
		model.addAttribute("user", user);
		return "views/web/user/user";
	}
}
