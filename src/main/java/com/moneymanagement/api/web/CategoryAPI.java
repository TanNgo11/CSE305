package com.moneymanagement.api.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moneymanagement.dto.ApiResponse;
import com.moneymanagement.dto.CategoryDTO;
import com.moneymanagement.service.impl.CategoryService;

@RestController(value = "categoryAPI")
public class CategoryAPI {
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/api/categories")
	public ResponseEntity<List<CategoryDTO>> getAllCategory() {
		return new ResponseEntity<List<CategoryDTO>>(categoryService.getAllCategory(), HttpStatus.OK);
	}

	@GetMapping("/api/category/{id}")
	public ResponseEntity<CategoryDTO> getCateByID(@PathVariable("id") long id) {
		return new ResponseEntity<CategoryDTO>(categoryService.getCategoryByID(id), HttpStatus.OK);
	}
}
