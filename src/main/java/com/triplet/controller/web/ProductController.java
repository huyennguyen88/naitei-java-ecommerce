package com.triplet.controller.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.triplet.bean.ProductInfo;
import com.triplet.model.Product;
import com.triplet.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController extends BaseController {
	
	@Autowired
	private ProductService productService;
	
	private List<Product> products;
	
	@GetMapping("/{id}")
	public String detail(@PathVariable int id, Model model) {
		Product product = productService.findById(id);
		ProductInfo productInfo = new ProductInfo(product);
		model.addAttribute("product", productInfo);	
		return "views/web/products/product";
	}
	
	@GetMapping("/search")
	public @ResponseBody List<String> searchAuto(@RequestParam String term, Model model) {
		List<String> productNames = productService.searchByName(term);
		model.addAttribute("productNames",productNames);
		return productNames;
	}
	
	@GetMapping("/search-submit")
	public String searchSubmit(@RequestParam String name, Model model, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {
		
		this.products = productService.findByName(name);
		
		String url = "/products/paging";
		String endPoint = "views/web/products/products";
		
		return Pagination(url ,page, size, this.products, model, endPoint);
	}
	
	@GetMapping(value = "/paging")
	public String loadPagination(@RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, Model model) {
		
		String url = "/products/paging";
		String endPoint = "views/web/products/products";
		
		return Pagination(url ,page, size, this.products, model, endPoint);
	}

}
