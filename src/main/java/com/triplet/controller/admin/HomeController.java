package com.triplet.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class HomeController {
	@GetMapping(value = { "", "/" })
	public String index() {
		return "views/admin/home/index";
	}
}
