package com.moneymanagement.api.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moneymanagement.dto.ApiResponse;
import com.moneymanagement.dto.ExpenseDTO;
import com.moneymanagement.service.IExpenseService;

@RestController(value = "expenseAPI")
public class ExpenseAPI {

	@Autowired
	private IExpenseService expenseService;

	@GetMapping("/api/expenses")
	public ResponseEntity<List<ExpenseDTO>> getAllexpenses() {

		return new ResponseEntity<List<ExpenseDTO>>(expenseService.getAllExpense(), HttpStatus.OK);
	}

	@GetMapping("/api/expenses/sortBy")
	public ResponseEntity<ExpenseDTO> getAllExpensesByMonthAndYear(
			@RequestParam(name = "month", required = false) int month,
			@RequestParam(name = "year", required = false) int year, @RequestParam("page") int page,
			@RequestParam("limit") int limit) {

		ExpenseDTO result = new ExpenseDTO();
		result.setPage(page);
		result.setLimit(limit);

		Pageable pageable = new PageRequest(page - 1, limit);
		result.setListResult(expenseService.findAllExpensesByMonthAndYear(month, year));
		result.setTotalItem(expenseService.getTotalExpense());
		result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getLimit()));

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/api/expense/{id}")
	public ResponseEntity<ExpenseDTO> getExpensebyID(@PathVariable("id") long id) {
		return new ResponseEntity<ExpenseDTO>(expenseService.getExpenseByID(id), HttpStatus.OK);
	}

	@PostMapping("/api/expense")
	public ResponseEntity<ExpenseDTO> saveExpenseEntity(@RequestParam(name = "amount") double amount,
			@RequestParam(name = "description") String description, @RequestParam(name = "cateId") long cateId) {

		ExpenseDTO expenseDTO = new ExpenseDTO();
		expenseDTO.setAmount(amount);
		expenseDTO.setDescription(description);
		return new ResponseEntity<ExpenseDTO>(expenseService.saveExpenseDTO(expenseDTO, cateId), HttpStatus.OK);
	}

	@DeleteMapping("/api/expense/{id}")
	public ResponseEntity<ApiResponse> deleteExpenseByid(@PathVariable long id) {
		return new ResponseEntity<ApiResponse>(expenseService.deleteExpenseByID(id), HttpStatus.OK);
	}

	@PutMapping("/api/expense")
	public ResponseEntity<ExpenseDTO> editResponEntities(@RequestBody ExpenseDTO expenseDTO) {
		return new ResponseEntity<ExpenseDTO>(expenseService.editExpenseDTO(expenseDTO), HttpStatus.OK);
	}

}
