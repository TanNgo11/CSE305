package com.moneymanagement.api.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

import com.moneymanagement.constant.SystemConstant;
import com.moneymanagement.dto.ApiResponse;
import com.moneymanagement.dto.ExpenseDTO;
import com.moneymanagement.entity.AccountEntity;
import com.moneymanagement.repository.UserRepository;
import com.moneymanagement.service.IExpenseService;
import com.moneymanagement.utils.ExpenseExcelExporter;
import com.moneymanagement.utils.SecurityUtils;

@RestController(value = "expenseAPI")
public class ExpenseAPI {

	@Autowired
	private IExpenseService expenseService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/api/expenses")
	public ResponseEntity<ExpenseDTO> getAllexpensesByAccount(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer limit) {

		AccountEntity accountEntity = userRepository
				.findOneByUserNameAndStatus(SecurityUtils.getPrincipal().getUsername(), SystemConstant.ACTIVE_STATUS);

		ExpenseDTO result = new ExpenseDTO();
		if (page != null && limit != null) {

			Pageable pageable = new PageRequest(page - 1, limit);
			result.setPage(page);
			result.setLimit(limit);
			result.setListResult(expenseService.getAllExpenseByAccount(accountEntity, pageable));
			result.setTotalItem(expenseService.getTotalExpenseByAccount(accountEntity));
			result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getLimit()));
		} else {

			result.setListResult(expenseService.getAllExpenseByAccount(accountEntity));
		}

		return new ResponseEntity<ExpenseDTO>(result, HttpStatus.OK);
	}

//	@GetMapping("/api/expenses/sortBy")
//	public ResponseEntity<ExpenseDTO> getAllExpensesByMonthAndYear(
//			@RequestParam(name = "month", required = false) int month,
//			@RequestParam(name = "year", required = false) int year, @RequestParam("page") int page,
//			@RequestParam("limit") int limit) {
//
//		ExpenseDTO result = new ExpenseDTO();
//		result.setPage(page);
//		result.setLimit(limit);
//
//		Pageable pageable = new PageRequest(page - 1, limit);
//		result.setListResult(expenseService.findAllExpensesByMonthAndYear(month, year));
//		result.setTotalItem(expenseService.getTotalExpense());
//		result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getLimit()));
//
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}

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

	@PutMapping("/api/expense/{id}")
	public ResponseEntity<ExpenseDTO> editResponEntities(@PathVariable long id, @RequestBody ExpenseDTO expenseDTO) {

		expenseDTO.setAmount(expenseDTO.getAmount());
		expenseDTO.setDescription(expenseDTO.getDescription());

		return new ResponseEntity<ExpenseDTO>(expenseService.editExpenseDTO(expenseDTO), HttpStatus.OK);
	}

	@GetMapping("/api/expenses/search")
	public ResponseEntity<ExpenseDTO> getExpensebyID(@RequestParam String q,
			@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer limit) {
		AccountEntity accountEntity = userRepository
				.findOneByUserNameAndStatus(SecurityUtils.getPrincipal().getUsername(), SystemConstant.ACTIVE_STATUS);

		ExpenseDTO result = new ExpenseDTO();
		if (page != null && limit != null) {

			Pageable pageable = new PageRequest(page - 1, limit);
			result.setPage(page);
			result.setLimit(limit);
			result.setListResult(expenseService.searchExpenses(q, accountEntity, pageable));
			result.setTotalItem(expenseService.countSearchExpenses(q, accountEntity));
			result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getLimit()));
		} else {

			result.setListResult(expenseService.searchExpenses(q, accountEntity));
		}

		return new ResponseEntity<ExpenseDTO>(result, HttpStatus.OK);
	}

	@GetMapping("/excel")
	public void exportToExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=expenses_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		AccountEntity accountEntity = userRepository
				.findOneByUserNameAndStatus(SecurityUtils.getPrincipal().getUsername(), SystemConstant.ACTIVE_STATUS);
		List<ExpenseDTO> listExpense = expenseService.getAllExpenseByAccount(accountEntity);

		ExpenseExcelExporter expenseExcelExporter = new ExpenseExcelExporter(listExpense);

		expenseExcelExporter.export(response);
	}

}
