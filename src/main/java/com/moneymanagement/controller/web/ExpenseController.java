package com.moneymanagement.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.moneymanagement.dto.ExpenseDTO;
import com.moneymanagement.service.IExpenseService;

@Controller
public class ExpenseController {

	@Autowired
	private IExpenseService expenseService;

	@RequestMapping(value = { "/expense/add" }, method = RequestMethod.GET)
	public ModelAndView addPage() {
		ModelAndView mav = new ModelAndView("web/add");
		return mav;
	}
	
	@RequestMapping(value = { "/expense" }, method = RequestMethod.GET)
	public ModelAndView pageExpense() {
		ModelAndView mav = new ModelAndView("web/expense");
		return mav;
	}

	
	@RequestMapping(value = { "/expense/search" }, method = RequestMethod.GET)
	public ModelAndView pageSearchExpense(	@RequestParam(required = false) String q) {
		ModelAndView mav = new ModelAndView("web/search");
		return mav;
	}

	@RequestMapping(value = { "/expense/edit/{id}" }, method = RequestMethod.GET)
	public ModelAndView editPage(@PathVariable long id) {
		ModelAndView mav = new ModelAndView("web/edit");

		
		return mav;
	}

}



