package com.moneymanagement.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.moneymanagement.utils.MessageUtil;

@Controller(value = "homeControllerOfWeb")
public class HomeController {

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView homePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("web/home");

		String msgParameter = request.getParameter("msg");

		if (msgParameter != null) {
			mav.addObject("msg", "add_success".equals(msgParameter) ? MessageUtil.SUCCESS_ADD : MessageUtil.ERROR_ADD);
		}

		return mav;
	}

}
