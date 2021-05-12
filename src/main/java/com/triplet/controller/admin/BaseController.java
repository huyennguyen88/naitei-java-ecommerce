package com.triplet.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.triplet.service.OrderItemService;
import com.triplet.service.OrderService;
import com.triplet.service.UserService;

public abstract class BaseController {
	
	@Autowired
	protected UserService userService;
	
	@Autowired
	protected OrderService orderService;
	
	@Autowired
	protected OrderItemService orderItemService;

    protected String handleRedirect(final RedirectAttributes redirectAttributes, String css, String msg, String redirectEndpoint) {
        redirectAttributes.addFlashAttribute("css", css);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:" + redirectEndpoint;
    }
}
