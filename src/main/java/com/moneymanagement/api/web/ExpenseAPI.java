package com.moneymanagement.api.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moneymanagement.entity.ExpenseEntity;
import com.moneymanagement.service.impl.ExpenseService;

@RestController(value = "expenseAPI")
public class ExpenseAPI {
	
	@Autowired 
	private ExpenseService expenseService;
	
	@GetMapping("/api/expenses")
	public List<ExpenseEntity> getAllexpenses() {
		return expenseService.getAllExpense();
	}
	
	@PostMapping("/api/expenses")
	public ExpenseEntity saveExpenseEntity(@RequestBody ExpenseEntity entity) {
		return expenseService.save(entity);
	}
	

}
