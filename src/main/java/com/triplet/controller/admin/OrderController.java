package com.triplet.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.triplet.bean.Item;
import com.triplet.bean.OrderInfo;
import com.triplet.model.Order;
import com.triplet.model.OrderItem;
import com.triplet.model.Order.Status;

@PropertySource("classpath:messages.properties")
@Controller(value = "orderControllerOfadmin")
@RequestMapping("/admin/orders")
public class OrderController extends BaseController {

	@Value("${msg_error_update}")
	private String msg_error_update;

	@Value("${msg_success_update}")	
	private String msg_success_update;

	@GetMapping(value = { "", "/" })
	public String index(@RequestParam("stt") Optional<Integer> stt, Model model) {
		List<Order> orders;
		if (stt.isPresent()) {
			Status status = Status.values()[stt.get()];
			orders = orderService.loadByStatus(status);
		} else
			orders = orderService.loadOrders();

		model.addAttribute("orders", orders);
		return "views/admin/orders/orders";
	}

	@GetMapping("/{id}/update-status")
	public String changeOrderStatus(@PathVariable("id") int id, @RequestParam int action, Model model,
			final RedirectAttributes redirectAttributes) {
		String typeCss = "error";
		String message = msg_error_update;

		if (orderService.updateStatus(id, action)) {
			typeCss = "sucsess";
			message = msg_success_update;
		}
		return handleRedirect(redirectAttributes, typeCss, message, "/admin/orders");
	}

	@GetMapping("/{id}")
	String showOrderDetail(@PathVariable("id") int id, Model model) {
		Order order = orderService.findById(id);
		List<OrderItem> orderItems = orderItemService.loadByOrderId(id);

		List<Item> items = Item.toItems(orderItems);
		OrderInfo orderInfo = new OrderInfo(order);
		orderInfo.setItems(items);
		orderInfo.setItemQuantity(items.size());

		model.addAttribute("orderInfo", orderInfo);
		return "views/admin/orders/order";
	}
}
