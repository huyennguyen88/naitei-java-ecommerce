package com.triplet.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.triplet.bean.ProductInfo;
import com.triplet.model.Product;
import com.triplet.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/{id}")
	public String product(@PathVariable int id, Model model) {
		Product product = productService.findById(id);
		ProductInfo productInfo = new ProductInfo(product);
		model.addAttribute("product", productInfo);	
		return "views/web/product/product";
	}
}
