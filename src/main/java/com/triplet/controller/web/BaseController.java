package com.triplet.controller.web;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.triplet.service.impl.BaseServiceImpl;
import com.triplet.service.impl.MyUser;

public abstract class BaseController {
	protected MyUser loadCurrentUser() {
		MyUser user = (MyUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}

	protected String handleRedirect(final RedirectAttributes redirectAttributes, String css, String msg,
			String redirectEndpoint) {
		redirectAttributes.addFlashAttribute("css", css);
		redirectAttributes.addFlashAttribute("msg", msg);
		return "redirect:" + redirectEndpoint;
	}

	protected <T> String Pagination(int id, Optional<Integer> page, Optional<Integer> size, List<T> objects,
			Model model, String returnEndpoint) {
		int currentPage = page.orElse(1);
		int pageSize = size.orElse(20);
		Page<T> objectPage = BaseServiceImpl.findPaginationBase(PageRequest.of(currentPage - 1, pageSize), objects);

		int totalPages = objectPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		model.addAttribute("objectPage", objectPage);
		model.addAttribute("id", id);
		model.addAttribute("currentPage", currentPage);
		return returnEndpoint;
	}
}
