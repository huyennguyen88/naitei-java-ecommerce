package com.triplet.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.triplet.model.Order.Status;
import com.triplet.model.Role;
import com.triplet.utils.ConstantUtils;

@Controller(value = "admin-home")
@RequestMapping("/dashboard")
public class HomeController extends BaseController {
	@GetMapping(value = { "", "/" })

	public String index(Model model) {

		int customerNum = getNumberOfCustomer();
		String formatTotalRevenue = orderService.getTotalRevenue();
		int successOrderNum = orderService.loadByStatus(Status.ACCEPTED).size();
		int pendingOrderNum = orderService.loadByStatus(Status.PENDING).size();

		model.addAttribute("customerNum", customerNum);
		model.addAttribute("formatTotalRevenue", formatTotalRevenue);
		model.addAttribute("successOrderNum", successOrderNum);
		model.addAttribute("pendingOrderNum", pendingOrderNum);

		return "views/admin/home/index";
	}

	private int getNumberOfCustomer() {
		Role role = new Role();
		role.setId(ConstantUtils.ROLE_USER);
		role.setCode("USER");
		int customerNum = userService.loadUsers(role).size();
		return customerNum;
	}
}
