package com.moneymanagement.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.moneymanagement.utils.MessageUtil;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {

	

	@RequestMapping(value = {  "/admin/home" }, method = RequestMethod.GET)
	public ModelAndView homePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/home");

		if (request.getParameter("msg") != null) {

			if (request.getParameter("msg").equals("add_success"))
				mav.addObject("msg", MessageUtil.SUCCESS_ADD);
			else {
				mav.addObject("msg", MessageUtil.ERROR_ADD);
			}
		}

		return mav;
	}

	
}
