package com.moneymanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.moneymanagement.dto.BudgetDTO;
import com.moneymanagement.repository.BudgetRepository;
import com.moneymanagement.service.IBudgetService;

public class BudgetService implements IBudgetService{
	
	@Autowired
	private BudgetRepository BudgetRepository;
	
	@Override
	@Transactional
	public BudgetDTO save(BudgetDTO dto) {
		BudgetRepository.sa
		return null;
	}

}
