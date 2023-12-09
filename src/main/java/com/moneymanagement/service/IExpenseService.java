package com.moneymanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.moneymanagement.dto.ApiResponse;
import com.moneymanagement.dto.ExpenseDTO;
import com.moneymanagement.entity.AccountEntity;

public interface IExpenseService {
	List<ExpenseDTO> getAllExpenseByAccount(AccountEntity accountEntity ,Pageable pageable);
	
	List<ExpenseDTO> getAllExpenseByAccount(AccountEntity accountEntity );

	ExpenseDTO saveExpenseDTO(ExpenseDTO entity, long cateId);

	ExpenseDTO getExpenseByID(long id);
	
	ApiResponse deleteExpenseByID(long id);
	
	ExpenseDTO editExpenseDTO(ExpenseDTO expenseDTO);
	
	List<ExpenseDTO> findAllExpensesByMonthAndYear(int month, int year);
	
	int getTotalExpenseByAccount(AccountEntity accountEntity);
	
	int countSearchExpenses(String query, AccountEntity accountEntity);
	
	List<ExpenseDTO> searchExpenses(String searchTerm,AccountEntity accountEntity);
	
	List<ExpenseDTO> searchExpenses(String searchTerm,AccountEntity accountEntity,Pageable pageable);
}
