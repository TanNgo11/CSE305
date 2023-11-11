package com.moneymanagement.service;

import java.util.List;

import com.moneymanagement.entity.CategoryEntity;

public interface ICategoryService {

	CategoryEntity getCategoryByID(long id);

	List<CategoryEntity> getAllCategory();
}
