package com.moneymanagement.api.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.moneymanagement.entity.CategoryEntity;
import com.moneymanagement.service.impl.CategoryService;

@RestController(value = "categoryAPI")
public class CategoryAPI {
	@Autowired 
	private CategoryService categoryService;
	
	@GetMapping("/api/categories")
	public List<CategoryEntity> getAllCategory() {
		return categoryService.getAllCategory();
	}
	@GetMapping("/api/category/{id}")
	public CategoryEntity getCateByID(@PathVariable("id") long id) {
		return categoryService.getCategoryByID(id);
	}

}
