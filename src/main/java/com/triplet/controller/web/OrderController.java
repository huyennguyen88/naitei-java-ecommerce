package com.triplet.controller.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.triplet.bean.Cart;
import com.triplet.bean.Item;
import com.triplet.bean.OrderInfo;
import com.triplet.bean.Ship;
import com.triplet.model.Order;
import com.triplet.model.OrderItem;
import com.triplet.model.User;
import com.triplet.service.impl.MyUser;

@PropertySource("classpath:messages.properties")
@Controller
@RequestMapping("/orders")
public class OrderController extends BaseController {

	@Value("${msg_error_order_add}")
	private String msg_error_order_add;

	@Value("${msg_success_order_add}")
	private String msg_success_order_add;

	@GetMapping(value = { "/", "" })
	public String loadOrders(Model model) {
		MyUser user = loadCurrentUser();
		List<Order> orders = orderService.loadByUserId(user.getId());
		List<OrderInfo> orderInfos = new ArrayList<OrderInfo>();
		if(!orders.isEmpty()) {
			orderInfos = OrderInfo.ordersToOrderInfos(orders);
		}
		model.addAttribute("orderInfos", orderInfos);
		return "views/web/users/orders";
	}

	@GetMapping("/{id}")
	public String show(@PathVariable("id") int id, final RedirectAttributes redirectAttributes, Model model) {
		Order order = orderService.findById(id);
		List<OrderItem> orderItems = orderItemService.loadByOrderId(id);
		
		List<Item> items = Item.toItems(orderItems);
		OrderInfo orderInfo = new OrderInfo(order);
		orderInfo.setItems(items);
		orderInfo.setItemQuantity(items.size());

		model.addAttribute("orderInfo", orderInfo);
		return "views/web/users/order";
	}

	@GetMapping("/ship-new")
	public String showShippingForm(Model model, HttpSession session) {

		MyUser currentUser = loadCurrentUser();
		User user = userService.findById(currentUser.getId());
		Cart cart = (Cart) session.getAttribute("cart");
		cart.setUser(user);

		Ship ship = new Ship();
		ship.setAddress(user.getAddress());
		ship.setFullname(user.getFullname());
		ship.setPhone(user.getPhone());

		session.setAttribute("cart", cart);
		model.addAttribute("ship", ship);
		return "views/web/orders/shipping-form";
	}

	@PostMapping("/ship-add")
	public String addShipping(@ModelAttribute("ship") Ship ship, HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		cart.setShip(ship);
		session.setAttribute("cart", cart);
		return "views/web/orders/confirm";
	}

	@GetMapping("/create")
	public String create(final RedirectAttributes redirectAttributes, HttpSession session, Model model) {
		String typeCss = "error";
		String message = msg_error_order_add;

		Cart cart = (Cart) session.getAttribute("cart");
		OrderInfo orderInfo = new OrderInfo(cart);

		if (orderService.createOrder(orderInfo)) {
			session.removeAttribute("cart");
			typeCss = "success";
			message = msg_success_order_add;
			return handleRedirect(redirectAttributes, typeCss, message, "/orders");
		}
		return handleRedirect(redirectAttributes, typeCss, message, "/cart/detail");
	}
}
