package com.triplet.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.triplet.model.User;
import com.triplet.service.UserService;

@Controller
@RequestMapping("/admin/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = { "", "/" })
	public String index(Model model) {
		List<User> users = userService.loadUsers();
		model.addAttribute("users", users);
		return "views/admin/user/users";
	}

}