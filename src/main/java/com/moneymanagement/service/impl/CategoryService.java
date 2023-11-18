package com.moneymanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.moneymanagement.dto.ApiResponse;
import com.moneymanagement.dto.CategoryDTO;
import com.moneymanagement.entity.CategoryEntity;
import com.moneymanagement.exception.ResourceNotFoundException;
import com.moneymanagement.repository.CategoryRepository;
import com.moneymanagement.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDTO getCategoryByID(long id) {
		if (categoryRepository.findOne(id) == null) {
			throw new ResourceNotFoundException("Category not exists!");
		}

		return mapper.map(categoryRepository.getOne(id), CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<CategoryEntity> listCategoryEntity = categoryRepository.findAll();

		List<CategoryDTO> listCategoryDTO = new ArrayList<>();

		for (CategoryEntity cateEntity : listCategoryEntity) {

			listCategoryDTO.add(mapper.map(cateEntity, CategoryDTO.class));
		}

		return listCategoryDTO;
	}

	@Override
	public CategoryDTO saveCategoryDTO(CategoryDTO categoryDTO) {

		CategoryEntity categoryEntity = categoryRepository.save(mapper.map(categoryDTO, CategoryEntity.class));

		return mapper.map(categoryEntity, CategoryDTO.class);
	}

	@Override
	public CategoryDTO editCategoryDTO(CategoryDTO categoryDTO) {
		CategoryEntity categoryEntity = categoryRepository.findOne(categoryDTO.getId());

		if (categoryEntity == null) {
			throw new ResourceNotFoundException("Category not exists!");
		}
		categoryEntity.setName(categoryDTO.getName());

		categoryRepository.save(categoryEntity);

		return mapper.map(categoryEntity, CategoryDTO.class);
	}

	@Override
	public ApiResponse deleteCategoryDTO(long id) {
		if (categoryRepository.findOne(id) == null) {
			throw new ResourceNotFoundException("Category not exists!");
		}

		categoryRepository.delete(id);
		
		 ApiResponse respone = new ApiResponse();
		 respone.setHttp(HttpStatus.ACCEPTED);
		 respone.setMessage("Delete successfull!");
		 respone.setSuccess(true);
		return respone;
	}

}
