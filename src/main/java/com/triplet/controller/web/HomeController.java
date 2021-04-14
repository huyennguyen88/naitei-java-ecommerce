package com.triplet.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.triplet.bean.UserInfo;
import com.triplet.service.UserService;
import com.triplet.validate.UserValidation;

@PropertySource("classpath:messages.properties")
@Controller
public class HomeController extends BaseController {
	private static final Logger logger = Logger.getLogger(HomeController.class);

	@Autowired
	private UserService userService;

	@Value("${msg_error_username_or_email}")
	private String msg_error_username_or_email;

	@Value("${msg_sucess_register}")
	private String msg_sucess_register;

	@GetMapping(value = { "/", "/welcome" })
	public String index(Model model) {
		logger.info("home page");
		return "views/web/home/index";
	}

	@GetMapping("/login")
	public String login() {
		logger.info("login ne");
		return "views/signin/signin";
	}

	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/welcome";
	}

	@GetMapping(value = "/accessDenied")
	public String accessDenied() {
		return "redirect:/login?accessDenied";
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new UserInfo());
		return "views/signup/signup";
	}

	@PostMapping("/register-process")
	public String register(@ModelAttribute("user") UserInfo userInfo, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		logger.info("register");
		UserValidation userVali = new UserValidation();
		userVali.validate(userInfo, result);
		if (result.hasErrors()) {
			return "views/signup/signup";
		} else if (userService.createUser(userInfo.convertToUser()) == false) {
			model.addAttribute("error",msg_error_username_or_email);
			return "views/signup/signup";
		}
		userService.createUser(userInfo.convertToUser());
		redirectAttributes.addFlashAttribute("registersuccess", msg_sucess_register);
		logger.info(redirectAttributes.getAttribute("registersuccess"));
		return "views/signin/signin";
	}
}
