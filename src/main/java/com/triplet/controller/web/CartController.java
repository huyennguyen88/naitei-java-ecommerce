package com.triplet.controller.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.triplet.bean.Cart;
import com.triplet.bean.Item;
import com.triplet.model.Product;

@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {

	@GetMapping("/add/{id}")
	public String add(@PathVariable("id") int id, HttpSession session, HttpServletRequest request) {
		Product product = productService.findById(id);
		Item newItem = new Item(product);
		Cart cart = new Cart();
		if (session.getAttribute("cart") != null) {
			cart = (Cart) session.getAttribute("cart");
		}
		cart.add(newItem);
		session.setAttribute("cart", cart);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	@GetMapping("/detail")
	public String show(HttpSession session, Model model) {
		Cart cart = (Cart) session.getAttribute("cart");
		model.addAttribute("cart", cart);
		return "views/web/orders/cart";
	}

	@GetMapping("/change/{id}")
	public String changeAmount(@PathVariable("id") int id, @RequestParam("action") String action,
			HttpServletRequest request, HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		if (action.equals("plus") && isInstock(id))
			cart.plus(id);
		else
			cart.minus(id);
		session.setAttribute("cart", cart);
		String referer = request.getHeader("Referer");
		return "redirect:" + referer;
	}

	private boolean isInstock(int productId) {
		Product product = productService.findById(productId);
		if (product.getQuantity() > 0)
			return true;
		return false;
	}
}