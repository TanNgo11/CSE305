package com.moneymanagement.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.moneymanagement.dto.AccountDTO;
import com.moneymanagement.dto.ApiResponse;
import com.moneymanagement.service.IAccountService;

@RestController(value = "AccountAPI")
public class AccountAPI {

	@Autowired
	private IAccountService accountService;

	@GetMapping("/admin/api/accounts")
	public ResponseEntity<AccountDTO> getAllAccounts(@RequestParam int page, @RequestParam int limit) {
		AccountDTO result = new AccountDTO();
		Pageable pageable = new PageRequest(page - 1, limit);

		result.setPage(page);
		result.setLimit(limit);
		result.setListResult(accountService.getAllAccounts(pageable));
		result.setTotalItem(accountService.getTotalItem());
		result.setTotalPage((int) Math.ceil((double) result.getTotalItem() / result.getLimit()));

		return new ResponseEntity<AccountDTO>(result, HttpStatus.OK);
	}

	@DeleteMapping("/admin/api/account/{id}")
	public ResponseEntity<ApiResponse> disableAccount(@PathVariable long id) {

		return new ResponseEntity<ApiResponse>(accountService.disableStatusAccount(id), HttpStatus.OK);
	}

	@PutMapping("/admin/api/account/{id}")
	public ResponseEntity<ApiResponse> activeAccount(@PathVariable long id) {

		return new ResponseEntity<ApiResponse>(accountService.activeStatusAccount(id), HttpStatus.OK);
	}

}
