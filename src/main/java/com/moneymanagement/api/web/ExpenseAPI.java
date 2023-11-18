package com.moneymanagement.api.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moneymanagement.dto.ApiResponse;
import com.moneymanagement.dto.CategoryDTO;
import com.moneymanagement.dto.ExpenseDTO;
import com.moneymanagement.entity.ExpenseEntity;
import com.moneymanagement.service.impl.ExpenseService;

@RestController(value = "expenseAPI")
public class ExpenseAPI {

	@Autowired
	private ExpenseService expenseService;

	@GetMapping("/api/expenses")
	public ResponseEntity<List<ExpenseDTO>> getAllexpenses() {

		return new ResponseEntity<List<ExpenseDTO>>(expenseService.getAllExpense(), HttpStatus.OK);
	}
	@GetMapping("/api/expense/{id}")
	public ResponseEntity<ExpenseDTO> getExpensebyID(@PathVariable("id") long id) {
		return new ResponseEntity<ExpenseDTO>(expenseService.getExpenseByID(id), HttpStatus.OK);
	}
	
	@PostMapping("/api/expense")
	public ResponseEntity<ExpenseDTO> saveExpenseEntity(@RequestBody ExpenseDTO entity) {
		return new ResponseEntity<ExpenseDTO>(expenseService.saveExpenseDTO(entity), HttpStatus.OK);
	}

	@DeleteMapping("/api/expense")
	public ResponseEntity<ApiResponse> deleteCategory(@RequestBody ExpenseDTO expenseDTO) {
		return new ResponseEntity<ApiResponse>(expenseService.deleteExpenseByID(expenseDTO.getId()), HttpStatus.OK);
	}
	@PutMapping("/api/expense")
	public ResponseEntity<ExpenseDTO> editResponEntities(@RequestBody ExpenseDTO expenseDTO) {
		return new ResponseEntity<ExpenseDTO>(expenseService.editExpenseDTO(expenseDTO), HttpStatus.OK);
	}

}
