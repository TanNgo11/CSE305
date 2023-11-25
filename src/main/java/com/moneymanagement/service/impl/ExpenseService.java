package com.moneymanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.moneymanagement.converter.ExpenseConverter;
import com.moneymanagement.dto.ApiResponse;
import com.moneymanagement.dto.CategoryDTO;
import com.moneymanagement.dto.ExpenseDTO;
import com.moneymanagement.entity.CategoryEntity;
import com.moneymanagement.entity.ExpenseEntity;
import com.moneymanagement.exception.ResourceNotFoundException;
import com.moneymanagement.repository.CategoryRepository;
import com.moneymanagement.repository.ExpenseRepository;
import com.moneymanagement.service.IExpenseService;

@Service
public class ExpenseService implements IExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ExpenseConverter expenseConverter;

	@Autowired
	private CategoryRepository categoryRepository;

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
		ExpenseEntity expenseEntity = expenseRepository.findOne(id);
		if (expenseEntity == null) {
			throw new ResourceNotFoundException("Expense not exists!");
		}
		CategoryEntity cateEntity = expenseEntity.getCategoryEntity();

		ExpenseDTO expenseDTO = mapper.map(expenseRepository.getOne(id), ExpenseDTO.class);
		expenseDTO.setCategoryDTO(mapper.map(cateEntity, CategoryDTO.class));

		return expenseDTO;
	}

	@Override
	public ExpenseDTO saveExpenseDTO(ExpenseDTO expenseDTO, long cateId) {
		CategoryEntity cateEntity = categoryRepository.findOne(cateId);
		ExpenseEntity expenseEntity = mapper.map(expenseDTO, ExpenseEntity.class);
		expenseEntity.setCategoryEntity(cateEntity);
		expenseRepository.save(expenseEntity);

		return mapper.map(expenseEntity, ExpenseDTO.class);
	}

	@Override
	public ApiResponse deleteExpenseByID(long id) {
		if (expenseRepository.findOne(id) == null) {
			throw new ResourceNotFoundException("Expense not exists!");
		}
		expenseRepository.delete(id);
		ApiResponse respone = new ApiResponse();
		respone.setHttp(HttpStatus.ACCEPTED);
		respone.setMessage("Delete successfull!");
		respone.setSuccess(true);
		return respone;
	}

	@Override
	public ExpenseDTO editExpenseDTO(ExpenseDTO expenseDTO) {

		ExpenseEntity expenseEntity = expenseRepository.findOne(expenseDTO.getId());

		if (expenseEntity == null) {
			throw new ResourceNotFoundException("Expense not exists!");
		}
		expenseEntity = expenseConverter.toEntity(expenseEntity, expenseDTO);

		expenseRepository.save(expenseEntity);

		return mapper.map(expenseEntity, ExpenseDTO.class);
	}

	@Override
	public List<ExpenseDTO> findAllExpensesByMonthAndYear(int month, int year, Pageable pageable) {
		List<ExpenseEntity> listExpenseEntity = expenseRepository.findAllExpensesByMonthAndYear(month, year, pageable)
				.orElseThrow(() -> new ResourceNotFoundException("Can not found any expense"));

		return listExpenseEntity.stream().map(expense -> expenseConverter.toDTO(new ExpenseDTO(), expense))
				.collect(Collectors.toList());

	}

	@Override
	public int getTotalExpense() {
		
		return (int) expenseRepository.count();
	}

}
