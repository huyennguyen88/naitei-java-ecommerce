package com.triplet.controller.web;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller(value = "home")
public class HomeController {
	private static final Logger logger = Logger.getLogger(HomeController.class);

	@GetMapping("/")
	public String index(HttpSession session, Model model) {
		logger.info("home page");

		return "views/web/home/index";
	}

}
