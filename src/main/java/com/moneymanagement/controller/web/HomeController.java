package com.moneymanagement.controller.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.moneymanagement.service.IExpenseService;
import com.moneymanagement.utils.MessageUtil;

@Controller
public class HomeController {
	
	@Autowired
	private IExpenseService expenseService;
	
	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public ModelAndView homePage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("web/home");
		
		
		
		if (request.getParameter("msg") != null) {
			
			if (request.getParameter("msg").equals("add_success"))
				mav.addObject("msg", MessageUtil.SUCCESS_ADD);
			else {
				mav.addObject("msg", MessageUtil.ERROR_ADD);
			}
		}
		
		
		return mav;
	}
	
	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
	public ModelAndView addPage() {
		ModelAndView mav = new ModelAndView("web/add");
		
		return mav;
	}

}
