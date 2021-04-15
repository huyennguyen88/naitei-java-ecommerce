package com.triplet.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.triplet.model.User;
import com.triplet.service.UserService;
import com.triplet.service.impl.MyUser;

@PropertySource("classpath:messages.properties")
@Controller
@RequestMapping("/users")
public class UserController extends BaseController {

	@Autowired
	UserService userService;

	@Value("${msg_success_update}")
	private String msg_success_update;

	@Value("${msg_error_update}")
	private String msg_error_update;

	@GetMapping("/{id}")
	public String redering(@PathVariable("id") int id, Model model, HttpServletRequest request,
			final RedirectAttributes redirectAttributes) {
		MyUser currentUser = loadCurrentUser();
		if (currentUser.getId() != id) {
			return "redirect:/login?accessDenied";
		}
		User user = userService.findById(currentUser.getId());
		model.addAttribute("user", user);
		return "views/web/user/user";
	}

	@PostMapping("/{id}/update")
	public String saveOrUpdate(@PathVariable("id") int id, Model model, @Valid User user, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		String typeCss = "error";
		String message = msg_error_update;

		User userOld = userService.findById(user.getId());
		user.setCreate_time(userOld.getCreate_time());
		user.setEmail(userOld.getEmail());
		user.setPassword(userOld.getPassword());
		user.setRoles(userOld.getRoles());
		user.setUsername(userOld.getUsername());
		if (userService.saveOrUpdate(user) == null) {
			return handleRedirect(redirectAttributes, typeCss, message, "/users/" + user.getId());
		}
		typeCss = "sucess";
		message = msg_success_update;
		return handleRedirect(redirectAttributes, typeCss, message, "/users/" + user.getId());
	}

}
