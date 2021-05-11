package com.triplet.controller.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.triplet.bean.ProductInfo;
import com.triplet.model.Product;
import com.triplet.model.Rate;
import com.triplet.service.CategoryService;
import com.triplet.service.OrderItemService;
import com.triplet.service.OrderService;
import com.triplet.service.ProductService;
import com.triplet.service.RateService;
import com.triplet.service.UserService;
import com.triplet.service.impl.BaseServiceImpl;
import com.triplet.service.impl.MyUser;

public abstract class BaseController {
	@Autowired
	protected RateService rateService;

	@Autowired
	protected ProductService productService;

	@Autowired
	protected UserService userService;

	@Autowired
	protected CategoryService categoryService;
	
	@Autowired
	protected OrderService orderService;
	
	@Autowired
	protected OrderItemService orderItemService;
	
	protected MyUser loadCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication instanceof AnonymousAuthenticationToken)
			return null;
		MyUser user = (MyUser) authentication.getPrincipal();
		return user;
	}

	protected String handleRedirect(final RedirectAttributes redirectAttributes, String css, String msg,
			String redirectEndpoint) {
		redirectAttributes.addFlashAttribute("css", css);
		redirectAttributes.addFlashAttribute("msg", msg);
		return "redirect:" + redirectEndpoint;
	}

	protected <T> String Pagination(String url, Optional<Integer> page, Optional<Integer> size, List<T> objects,
			Model model, String returnEndpoint) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(20);
		Page<T> objectPage = BaseServiceImpl.findPaginationBase(PageRequest.of(currentPage - 1, pageSize), objects);

		int totalPages = objectPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("objectPage", objectPage);
		model.addAttribute("url", url);
		model.addAttribute("currentPage", currentPage);
		return returnEndpoint;
	}

	protected List<ProductInfo> generateProductInfos(List<Product> products) {
		List<ProductInfo> productInfos = new ArrayList<>();
		for (Product product : products) {
			List<Rate> rates = rateService.loadRatings(product.getId());
			product.setRates(rates);
			ProductInfo proInfo = new ProductInfo(product);
			productInfos.add(proInfo);
		}
		return productInfos;
	}
}
