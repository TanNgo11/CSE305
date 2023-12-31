package com.moneymanagement.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.moneymanagement.dto.ExpenseDTO;
import com.moneymanagement.service.IExpenseService;

@Controller
public class ExpenseController {

	@Autowired
	private IExpenseService expenseService;

	@RequestMapping(value = { "/add" }, method = RequestMethod.GET)
	public ModelAndView addPage() {
		ModelAndView mav = new ModelAndView("web/add");
		return mav;
	}

	@RequestMapping(value = { "/edit/{id}" }, method = RequestMethod.GET)
	public ModelAndView editPage(@PathVariable long id) {
		ModelAndView mav = new ModelAndView("web/edit");

		ExpenseDTO expenseDTO = expenseService.getExpenseByID(id);
		mav.addObject("id", expenseDTO.getId());
		mav.addObject("amount", expenseDTO.getAmount());
		mav.addObject("category", expenseDTO.getCategoryDTO().getId());
		mav.addObject("description", expenseDTO.getDescription());
		return mav;
	}

}
