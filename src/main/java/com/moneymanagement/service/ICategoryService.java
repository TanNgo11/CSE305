package com.moneymanagement.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.moneymanagement.dto.ApiResponse;
import com.moneymanagement.dto.CategoryDTO;

public interface ICategoryService {

	CategoryDTO getCategoryByID(long id);

	List<CategoryDTO> getAllCategory(Pageable pageable);

	CategoryDTO saveCategoryDTO(CategoryDTO categoryDTO);

	CategoryDTO editCategoryDTO(CategoryDTO categoryDTO);

	ApiResponse disableStatusCate(long id);
	
	ApiResponse activeStatusCate(long id);

	int getTotalItem();

	List<CategoryDTO> getAllCategory();

}
