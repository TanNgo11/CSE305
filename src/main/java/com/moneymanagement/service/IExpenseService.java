package com.moneymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.moneymanagement.dto.ApiResponse;
import com.moneymanagement.dto.ExpenseDTO;

public interface IExpenseService {
	List<ExpenseDTO> getAllExpense();

	ExpenseDTO saveExpenseDTO(ExpenseDTO entity, long cateId);

	ExpenseDTO getExpenseByID(long id);
	
	ApiResponse deleteExpenseByID(long id);
	
	ExpenseDTO editExpenseDTO(ExpenseDTO expenseDTO);
	
	List<ExpenseDTO> findAllExpensesByMonthAndYear(int month, int year, Pageable pageable);
	
	int getTotalExpense();
}
