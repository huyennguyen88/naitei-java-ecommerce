package com.triplet.controller.web;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.triplet.bean.ProductInfo;
import com.triplet.bean.RateInfo;
import com.triplet.model.Product;
import com.triplet.model.Rate;
import com.triplet.service.impl.MyUser;
import com.triplet.utils.RateUtils;

@Controller
@PropertySource("classpath:messages.properties")
@RequestMapping("/products")
public class ProductController extends BaseController {

	private List<ProductInfo> productInfos;

	@GetMapping("/{id}")
	public String show(@PathVariable int id, Model model) {

		Product product = productService.findById(id);
		List<Rate> rates = rateService.loadRatings(product.getId());
		product.setRates(rates);

		ProductInfo productInfo = new ProductInfo(product);
		RateUtils rateUtils = new RateUtils(rates);
		int[] ratesPercent = rateUtils.getRatesPercent();

		String rateStatus = "add";

		RateInfo rateInfo = new RateInfo();
		Rate rateOfCurrentUser = new Rate();

		// Product's rates have element & user is logged in
		if (rates.size() > 0 && loadCurrentUser() != null) {
			MyUser currentUser = loadCurrentUser();
			for (Rate rate : rates) {
				// User rated
				if (rate.getUser().getId() == currentUser.getId()) {
					rateStatus = "update";
					rateOfCurrentUser = rate;
					break;
				}
			}
			rateInfo = setRateInfo(rateOfCurrentUser);
		}

		model.addAttribute("product", productInfo);
		model.addAttribute("rates", rates);
		model.addAttribute("ratesPercent", ratesPercent);
		model.addAttribute("rateStatus", rateStatus);
		model.addAttribute("rateInfo", rateInfo);
		return "views/web/products/product";
	}

	@GetMapping("/search")
	public @ResponseBody List<String> searchAuto(@RequestParam String term, Model model) {
		List<String> productNames = productService.getListNamesOfProduct(term);
		model.addAttribute("productNames", productNames);
		return productNames;
	}

	@GetMapping("/search-submit")
	public String searchSubmit(@RequestParam String name, Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		List<Product> products = productService.findByName(name);
		this.productInfos = generateProductInfos(products);

		String url = "/products/paging";
		String endPoint = "views/web/products/products";

		return Pagination(url, page, size, this.productInfos, model, endPoint);
	}

	@GetMapping(value = "/paging")
	public String loadPagination(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, Model model) {

		String url = "/products/paging";
		String endPoint = "views/web/products/products";

		return Pagination(url, page, size, this.productInfos, model, endPoint);
	}

	private RateInfo setRateInfo(Rate rate) {
		RateInfo rateInfo = new RateInfo();
		rateInfo.setContent(rate.getContent());
		rateInfo.setValue(rate.getValue());
		rateInfo.setId(rate.getId());
		return rateInfo;
	}
}