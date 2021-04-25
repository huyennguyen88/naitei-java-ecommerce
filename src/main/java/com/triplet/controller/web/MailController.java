package com.triplet.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.triplet.service.EmailService;

@PropertySource("classpath:messages.properties")
@Controller
public class MailController extends BaseController {
	
	@Autowired
	private EmailService emailService;
	
	@Value("${msg_success_mail}")
	private String msg_success_mail;

	@Value("${msg_error_mail}")
	private String msg_error_mail;

	@PostMapping("/send-email")
	public String doSendEmail(HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		String typeCss = "error";
		String message = msg_error_mail;
		
		String subject = request.getParameter("subject");
		String text = request.getParameter("message");
		
		if(emailService.sendSimpleMessage(subject, text)) {
			typeCss = "success";
			message = msg_success_mail;
		}
		return handleRedirect(redirectAttributes, typeCss, message, "/contact");

	}
}
