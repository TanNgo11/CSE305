package com.moneymanagement.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.moneymanagement.dto.BudgetDTO;
import com.moneymanagement.service.IBudgetService;

@RestController(value = "BudgetAPI")
public class BudgetAPI {

	@Autowired
	private IBudgetService budgetService;

	@PostMapping("/api/budget")
	public ResponseEntity<BudgetDTO> saveBudget(@RequestBody BudgetDTO budgetDTO) {

		return new ResponseEntity<BudgetDTO>(budgetService.save(budgetDTO), HttpStatus.OK);

	}

}
