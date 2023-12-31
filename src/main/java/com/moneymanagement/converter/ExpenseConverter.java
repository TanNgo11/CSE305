package com.moneymanagement.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moneymanagement.dto.CategoryDTO;
import com.moneymanagement.dto.ExpenseDTO;
import com.moneymanagement.entity.CategoryEntity;
import com.moneymanagement.entity.ExpenseEntity;

@Component
public class ExpenseConverter {
	
	@Autowired
	private ModelMapper mapper;
	
	public ExpenseEntity toEntity(ExpenseEntity entity, ExpenseDTO dto) {
		entity.setAmount(dto.getAmount());
		entity.setDescription(dto.getDescription());
		entity.setCategoryEntity(mapper.map(dto.getCategoryDTO(), CategoryEntity.class));
		
		return entity;
	}
	
	public ExpenseDTO toDTO( ExpenseDTO dto,ExpenseEntity entity) {
		dto.setId(entity.getId());
		dto.setAmount(entity.getAmount());
		dto.setDescription(entity.getDescription());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedDate(entity.getModifiedDate());
		dto.setCategoryDTO(mapper.map(entity.getCategoryEntity(), CategoryDTO.class));
		return dto;
	}

}
