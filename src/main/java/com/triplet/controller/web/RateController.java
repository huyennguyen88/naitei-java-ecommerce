package com.triplet.controller.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.triplet.bean.RateInfo;
import com.triplet.model.Product;
import com.triplet.model.Rate;
import com.triplet.model.User;
import com.triplet.service.impl.MyUser;

@Controller
@PropertySource("classpath:messages.properties")
@RequestMapping("/rates")
public class RateController extends BaseController {

	@Value("${msg_success_save_rate}")
	private String msg_success_save_rate;

	@Value("${msg_error_save_rate}")
	private String msg_error_save_rate;

	@Value("${msg_success_delete_rate}")
	private String msg_success_delete_rate;

	@Value("${msg_error_delete_rate}")
	private String msg_error_delete_rate;

	@PostMapping("/{id}/save-or-update")
	public String saveOrUpdate(@PathVariable int id, @RequestParam int productId,
			@ModelAttribute("rateInfo") RateInfo rateInfo, RedirectAttributes redirectAttribute) {
		String typeCss = "error";
		String message = msg_error_save_rate;

		MyUser currentUser = loadCurrentUser();
		User user = userService.findById(currentUser.getId());
		Product product = productService.findById(productId);

		Rate rate;
		if (rateInfo.getId() > 0) {
			// update
			rate = rateService.findById(rateInfo.getId());
		} else {
			// add new
			rate = new Rate();
			rate.setUser(user);
			rate.setProduct(product);
		}
		rate.setContent(rateInfo.getContent());
		rate.setValue(rateInfo.getValue());
		if (rateService.saveOrUpdate(rate) != null) {
			typeCss = "success";
			message = msg_success_save_rate;
		}
		return handleRedirect(redirectAttribute, typeCss, message, "/products/" + productId);
	}

	@GetMapping("/{id}/delete")
	public String delete(@PathVariable int id, RedirectAttributes redirectAttribute) {
		String typeCss = "error";
		String message = msg_error_delete_rate;

		Rate rate = rateService.findById(id);
		MyUser currentUser = loadCurrentUser();

		if (rate.getUser().getId() != currentUser.getId()) {
			return handleRedirect(redirectAttribute, typeCss, message, "/products/" + rate.getProduct().getId());
		}

		if (rateService.delete(rate)) {
			typeCss = "success";
			message = msg_success_delete_rate;
		}
		return handleRedirect(redirectAttribute, typeCss, message, "/products/" + rate.getProduct().getId());
	}
}