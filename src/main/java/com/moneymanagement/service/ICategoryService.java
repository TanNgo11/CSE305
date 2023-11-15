package com.moneymanagement.service;

import java.util.List;

import com.moneymanagement.dto.ApiResponse;
import com.moneymanagement.dto.CategoryDTO;

public interface ICategoryService {

	CategoryDTO getCategoryByID(long id);

	List<CategoryDTO> getAllCategory();

	CategoryDTO saveCategoryDTO(CategoryDTO categoryDTO);

	CategoryDTO editCategoryDTO(CategoryDTO categoryDTO);

	ApiResponse deleteCategoryDTO(long id);
}
