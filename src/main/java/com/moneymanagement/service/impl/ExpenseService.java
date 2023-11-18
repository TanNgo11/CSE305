package com.moneymanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.moneymanagement.dto.ApiResponse;
import com.moneymanagement.dto.CategoryDTO;
import com.moneymanagement.dto.ExpenseDTO;
import com.moneymanagement.entity.CategoryEntity;
import com.moneymanagement.entity.ExpenseEntity;
import com.moneymanagement.exception.ResourceNotFoundException;
import com.moneymanagement.repository.ExpenseRepository;
import com.moneymanagement.service.IExpenseService;

@Service
public class ExpenseService implements IExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public List<ExpenseDTO> getAllExpense() {
		List<ExpenseEntity> lisExpenseEntity = expenseRepository.findAll();

		List<ExpenseDTO> listExpenseDTO = new ArrayList<>();
		
		
		for (ExpenseEntity expenseEntity : lisExpenseEntity) {
			CategoryDTO cateDTO = mapper.map(expenseEntity.getCategoryEntity(), CategoryDTO.class);
			ExpenseDTO expenseDTO = mapper.map(expenseEntity, ExpenseDTO.class);
			expenseDTO.setCategoryDTO(cateDTO);
			
			listExpenseDTO.add(expenseDTO);
		}
		return listExpenseDTO;
	}


	@Override
	public ExpenseDTO getExpenseByID(long id) {
		ExpenseEntity expenseEntity =expenseRepository.findOne(id);
		if ( expenseEntity== null) {
			throw new ResourceNotFoundException("Expense not exists!");
		}
		CategoryEntity cateEntity = expenseEntity.getCategoryEntity();
		
		ExpenseDTO expenseDTO = mapper.map(expenseRepository.getOne(id), ExpenseDTO.class);
		expenseDTO.setCategoryDTO(mapper.map(cateEntity, CategoryDTO.class));
		

		return expenseDTO;
	}

	@Override
	public ExpenseDTO saveExpenseDTO(ExpenseDTO expenseDTO) {

		ExpenseEntity expenseEntity = expenseRepository.save(mapper.map(expenseDTO, ExpenseEntity.class));
		return mapper.map(expenseEntity, ExpenseDTO.class);
	}

	@Override
	public ApiResponse deleteExpenseByID(long id) {
		if (expenseRepository.findOne(id) == null) {
			throw new ResourceNotFoundException("Expense not exists!");
		}
		ApiResponse respone = new ApiResponse();
		respone.setHttp(HttpStatus.ACCEPTED);
		respone.setMessage("Delete successfull!");
		respone.setSuccess(true);
		return respone;
	}

	@Override
	public ExpenseDTO editExpenseDTO(ExpenseDTO expenseDTO) {
		
		ExpenseEntity expenseEntity = expenseRepository.findOne(expenseDTO.getId());

		
		if(expenseEntity == null) {
			throw new ResourceNotFoundException("Expense not exists!");
		}
		expenseEntity.setAmount(expenseDTO.getAmount());
		expenseEntity.setDate(expenseDTO.getCreatedDate());
		expenseEntity.setDescription(expenseDTO.getDescription());
		expenseEntity.setCategoryEntity(mapper.map(expenseDTO.getCategoryDTO(), CategoryEntity.class));
		expenseRepository.save(expenseEntity);
		
		return mapper.map(expenseEntity, ExpenseDTO.class);
	}

}
