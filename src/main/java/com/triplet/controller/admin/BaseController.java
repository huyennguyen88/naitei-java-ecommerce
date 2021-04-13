package com.triplet.controller.admin;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public abstract class BaseController {

    protected String handleRedirect(final RedirectAttributes redirectAttributes, String css, String msg, String redirectEndpoint) {
        redirectAttributes.addFlashAttribute("css", css);
        redirectAttributes.addFlashAttribute("msg", msg);
        return "redirect:" + redirectEndpoint;
    }
}
