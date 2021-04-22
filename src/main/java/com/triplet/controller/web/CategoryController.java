package com.triplet.controller.web;

import java.util.List;
import java.util.Optional;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.triplet.model.Product;
import com.triplet.service.ProductService;

@Controller
@RequestMapping("/categories")
public class CategoryController extends BaseController {

	Logger logger = Logger.getLogger(CategoryController.class);

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/{id}/products")
	public String loadProducts(@PathVariable int id, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, Model model) {
		List<Product> products = productService.loadFullProductsByCategory(id);
		return Pagination(id, page, size, products, model, "views/web/products/products");
	}
}
