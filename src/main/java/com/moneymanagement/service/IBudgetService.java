package com.moneymanagement.service;

import com.moneymanagement.dto.BudgetDTO;
import com.moneymanagement.entity.BudgetEntity;

public interface IBudgetService {
	BudgetDTO save(BudgetDTO dto);

	Double updateCurrentTarget();

	BudgetEntity findTheActiveBudget();

	BudgetDTO findTheActiveBudgetDTO();

}
