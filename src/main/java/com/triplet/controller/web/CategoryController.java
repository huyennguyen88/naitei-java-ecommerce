package com.triplet.controller.web;

import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.triplet.bean.ProductInfo;
import com.triplet.model.Product;

@Controller
@RequestMapping("/categories")
public class CategoryController extends BaseController {

	Logger logger = Logger.getLogger(CategoryController.class);

	private List<ProductInfo> productInfos;

	@GetMapping(value = "/{id}/products")
	public String loadProducts(@PathVariable int id, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, Model model) {

		List<Product> products = productService.loadFullProductsByCategory(id);
		this.productInfos = generateProductInfos(products);

		String url = "/categories/paging";
		String endPoint = "views/web/products/products";
		return Pagination(url, page, size, this.productInfos, model, endPoint);
	}

	@GetMapping(value = "/paging")
	public String loadPagination(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, Model model) {

		String url = "/categories/paging";
		String endPoint = "views/web/products/products";

		return Pagination(url, page, size, this.productInfos, model, endPoint);
	}
}
