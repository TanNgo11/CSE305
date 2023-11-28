package com.moneymanagement.api.admin;

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
import org.springframework.web.bind.annotation.RequestMapping;

import com.moneymanagement.dto.ApiResponse;
import com.moneymanagement.dto.CategoryDTO;
import com.moneymanagement.service.impl.CategoryService;

@RequestMapping(value = "adminCategoryAPI")
public class CategoryAPI {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/admin/api/categories")
	public ResponseEntity<List<CategoryDTO>> getAllCategory() {
		return new ResponseEntity<List<CategoryDTO>>(categoryService.getAllCategory(), HttpStatus.OK);
	}

	@GetMapping("/admin/api/category/{id}")
	public ResponseEntity<CategoryDTO> getCateByID(@PathVariable("id") long id) {
		return new ResponseEntity<CategoryDTO>(categoryService.getCategoryByID(id), HttpStatus.OK);
	}

	@PostMapping("/admin/api/category")
	public ResponseEntity<CategoryDTO> saveResponEntities(@RequestBody CategoryDTO newCategoryDTO) {
		return new ResponseEntity<CategoryDTO>(categoryService.saveCategoryDTO(newCategoryDTO), HttpStatus.OK);
	}

	@PutMapping("/admin/api/category")
	public ResponseEntity<CategoryDTO> editResponEntities(@RequestBody CategoryDTO newCategoryDTO) {
		return new ResponseEntity<CategoryDTO>(categoryService.editCategoryDTO(newCategoryDTO), HttpStatus.OK);
	}

	@DeleteMapping("/admin/api/category")
	public ResponseEntity<ApiResponse> deleteCategory(@RequestBody CategoryDTO categoryDTO) {
		return new ResponseEntity<ApiResponse>(categoryService.deleteCategoryDTO(categoryDTO.getId()), HttpStatus.OK);
	}

}
