package com.moneymanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymanagement.entity.CategoryEntity;
import com.moneymanagement.repository.CategoryRepository;
import com.moneymanagement.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	@Override
	public CategoryEntity getCategoryByID(long id) {
		
		return categoryRepository.getOne(id);
	}

	@Override
	public List<CategoryEntity> getAllCategory() {
		return categoryRepository.findAll();
	}

}
