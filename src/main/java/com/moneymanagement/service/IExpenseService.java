package com.moneymanagement.service;

import java.util.List;

import com.moneymanagement.dto.ApiResponse;
import com.moneymanagement.dto.ExpenseDTO;

public interface IExpenseService {
	List<ExpenseDTO> getAllExpense();

	ExpenseDTO saveExpenseDTO(ExpenseDTO entity);

	ExpenseDTO getExpenseByID(long id);
	
	ApiResponse deleteExpenseByID(long id);
	
	ExpenseDTO editExpenseDTO(ExpenseDTO expenseDTO);
}
