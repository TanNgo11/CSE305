package com.moneymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moneymanagement.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long>{

	
}
