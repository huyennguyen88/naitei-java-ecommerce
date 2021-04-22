package com.triplet.controller.web;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.triplet.bean.ProductInfo;
import com.triplet.converter.ProductConverter;
import com.triplet.model.Product;
import com.triplet.service.ProductService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	Logger logger = Logger.getLogger(CategoryController.class);

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/{id}/products")
	public String category(@PathVariable int id, Model model) {
		ProductConverter productConverter = new ProductConverter();
		List<Product> products = productService.loadFullProductsByCategory(id);
		List<ProductInfo> productInfos = productConverter.convertToProductInfos(products);
		model.addAttribute("products", productInfos);
		return "views/web/products/products";
	}
}
