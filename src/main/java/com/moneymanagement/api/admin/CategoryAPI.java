package com.moneymanagement.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moneymanagement.dto.ApiResponse;
import com.moneymanagement.dto.CategoryDTO;
import com.moneymanagement.service.impl.CategoryService;


@RestController(value = "categoryAPIOfAdmin")
public class CategoryAPI {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/admin/api/categories")
	public ResponseEntity<CategoryDTO> getAllCategory(@RequestParam int page, @RequestParam int limit) {
		
	CategoryDTO result = new CategoryDTO();
	Pageable pageable = new PageRequest(page - 1, limit);

	result.setPage(page);
	result.setLimit(limit);
	result.setListResult(categoryService.getAllCategory(pageable));
	result.setTotalItem(categoryService.getTotalItem());
	result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getLimit()));
	
	return new ResponseEntity<CategoryDTO>(result, HttpStatus.OK); 
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

	@DeleteMapping("/admin/api/category/{id}")
	public ResponseEntity<ApiResponse> disableCategory(@PathVariable long id) {

		return new ResponseEntity<ApiResponse>(categoryService.disableStatusCate(id), HttpStatus.OK);
	}

	@PutMapping("/admin/api/category/{id}")
	public ResponseEntity<ApiResponse> activeCategory(@PathVariable long id) {

		return new ResponseEntity<ApiResponse>(categoryService.activeStatusCate(id), HttpStatus.OK);
	}
}
