package com.moneymanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymanagement.dto.ExpenseDTO;
import com.moneymanagement.entity.AccountEntity;
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

		for (ExpenseEntity accountEntity : lisExpenseEntity) {
			listExpenseDTO.add(mapper.map(accountEntity, ExpenseDTO.class));
		}
		return listExpenseDTO;
	}

	@Override
	public ExpenseDTO getExpenseByID(long id) {
		if (expenseRepository.findOne(id) == null) {
			throw new ResourceNotFoundException("Expense not exists!");
		}
		
		return mapper.map(expenseRepository.getOne(id), ExpenseDTO.class);
	}

	@Override
	public ExpenseDTO saveExpenseDTO(ExpenseDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
