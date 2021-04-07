package com.triplet.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.triplet.bean.UserInfo;
import com.triplet.model.User;
import com.triplet.service.UserService;
import com.triplet.utils.ExcelFileUtil;
import com.triplet.validate.UserValidation;

@PropertySource("classpath:messages.properties")
@Controller(value="admin-user")
@RequestMapping("/admin/users")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	@Value("${msg_error_username_or_email}")
	private String msg_error_username_or_email;

	@Value("${msg_success_add_user}")
	private String msg_success_add_user;

	@Value("${msg_error_file}")
	private String msg_error_file;

	Logger logger = Logger.getLogger(UserController.class);

	@GetMapping(value = { "", "/" })
	public String index(Model model) {
		List<User> users = userService.loadUsers();
		model.addAttribute("users", users);
		return "views/admin/user/users";
	}

	@GetMapping("/new")
	public String register(Model model) {
		model.addAttribute("user", new UserInfo());
		model.addAttribute("status", "add");
		return "views/admin/user/user-form";
	}

	@PostMapping(value = "/create")
	public String addOneUser(@ModelAttribute("user") UserInfo userInfo, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes) {
		String typeCss = "error";
		String message = "Input error";
		UserValidation userVali = new UserValidation();
		userVali.validate(userInfo, result);
		if (result.hasErrors()) {
			return handleRedirect(redirectAttributes, typeCss, message, "/admin/users/new");
		}
		if (userService.createUser(userInfo.convertToUser()) == false) {
			message = msg_error_username_or_email;
			return handleRedirect(redirectAttributes, typeCss, message, "/admin/users/new");
		}
		userService.createUser(userInfo.convertToUser());
		typeCss = "success";
		message = msg_success_add_user;
		return handleRedirect(redirectAttributes, typeCss, message, "/admin/users");
	}

	@PostMapping(value = "/upload-excel")
	public String uploadExcel(@RequestParam("file") MultipartFile file, final RedirectAttributes redirectAttributes) {
		String typeCss = "error";
		String message = "Error!! Can't save list of users, maybe username or email existed!";
		if (!ExcelFileUtil.hasExcelFormat(file)) {
			message = msg_error_file;
			return handleRedirect(redirectAttributes, typeCss, message, "/admin/users");
		}
		try {
			if (ImportExcelFile(file)) {
				typeCss = "sucess";
				message = "Import list of users successfully!!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return handleRedirect(redirectAttributes, typeCss, message, "/admin/users");
	}

	private boolean ImportExcelFile(MultipartFile file) {
		List<User> users = new ArrayList<User>();
		List<UserInfo> listUserInfo = new ArrayList<>();
		ExcelFileUtil excelFileUtil = new ExcelFileUtil();

		listUserInfo = excelFileUtil.convertToUserInfos(file);
		if (listUserInfo == null)
			return false;
		users = userService.convertToUsers(listUserInfo);
		if (users == null)
			return false;
		return userService.saveBatch(users);
	}
}