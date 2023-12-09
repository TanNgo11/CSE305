package com.moneymanagement.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.moneymanagement.constant.SystemConstant;
import com.moneymanagement.dto.CategoryDTO;
import com.moneymanagement.service.ICategoryService;
import com.moneymanagement.utils.MessageUtil;

@Controller(value = "homeControllerOfAdmin")
public class HomeController {

	@Autowired
	private ICategoryService categoryService;

	@RequestMapping(value = { "/admin/home" }, method = RequestMethod.GET)
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

	@RequestMapping(value = { "/admin/category" }, method = RequestMethod.GET)
	public ModelAndView categorypage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/category");

		if (request.getParameter("msg") != null) {

			if (request.getParameter("msg").equals("add_success"))
				mav.addObject("msg", MessageUtil.SUCCESS_ADD);
			else {
				mav.addObject("msg", MessageUtil.ERROR_ADD);
			}
		}

		return mav;
	}

	@RequestMapping(value = { "/admin/category/add" }, method = RequestMethod.GET)
	public ModelAndView categoryAddPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/addCategory");
		return mav;
	}

	@RequestMapping(value = { "/admin/category/edit/{id}" }, method = RequestMethod.GET)
	public ModelAndView categoryEditPage(HttpServletRequest request, @PathVariable long id) {
		ModelAndView mav = new ModelAndView("admin/editCategory");
		CategoryDTO categoryDTO = categoryService.getCategoryByID(id);

		mav.addObject(categoryDTO);

		return mav;
	}

	@RequestMapping(value = { "/admin/category/add" }, method = RequestMethod.POST)
	public ModelAndView addCategoryPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("/admin/category");
		String categoryName = request.getParameter("categoryName");
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setName(categoryName);
		categoryDTO.setStatus(SystemConstant.ACTIVE_STATUS);

		categoryService.saveCategoryDTO(categoryDTO);

		return mav;
	}

	@RequestMapping(value = {"/admin/category/edit"}, method = RequestMethod.PUT)
	public ModelAndView editCategoryPage(@RequestParam("id") long id, @RequestParam("categoryName") String categoryName) {
		ModelAndView mav = new ModelAndView("/admin/category");
		
		
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setName(categoryName);
		categoryDTO.setId(id);
		categoryDTO.setStatus(SystemConstant.ACTIVE_STATUS);
		categoryService.editCategoryDTO(categoryDTO);
		

		return mav;
	}
	

	
}
