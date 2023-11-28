package com.moneymanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moneymanagement.constant.SystemConstant;
import com.moneymanagement.converter.BudgetConverter;
import com.moneymanagement.dto.BudgetDTO;
import com.moneymanagement.entity.AccountEntity;
import com.moneymanagement.entity.BudgetEntity;
import com.moneymanagement.entity.ExpenseEntity;
import com.moneymanagement.exception.ResourceNotFoundException;
import com.moneymanagement.exception.UserNotFoundException;
import com.moneymanagement.repository.BudgetRepository;
import com.moneymanagement.repository.UserRepository;
import com.moneymanagement.service.IBudgetService;
import com.moneymanagement.utils.SecurityUtils;

@Service
public class BudgetService implements IBudgetService {

	@Autowired
	private BudgetRepository budgetRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BudgetConverter budgetConverter;

	@Override
	@Transactional
	public BudgetDTO save(BudgetDTO dto) {

		if (SecurityUtils.getPrincipal() == null) {
			throw new UserNotFoundException("Not found User");
		}

		AccountEntity accountEntity = userRepository
				.findOneByUserNameAndStatus(SecurityUtils.getPrincipal().getUsername(), SystemConstant.ACTIVE_STATUS);

		BudgetEntity budgetEntity = findTheActiveBudget();

		if (budgetEntity == null) {
			budgetEntity = new BudgetEntity();
			budgetEntity.setAccountEntity(accountEntity);
			budgetEntity.setCurrentAmount(0.0);
			budgetEntity.setStatus(SystemConstant.ACTIVE_STATUS);

		}

		budgetRepository.save(budgetConverter.toEntity(budgetEntity, dto));

		return dto;
	}



	@Override
	public Double updateCurrentTarget() {
		Double updateAmount = 0.0;
		

		BudgetEntity budgetEntity = findTheActiveBudget();

		if (budgetEntity == null)
			throw new ResourceNotFoundException("Can not found any budget");

		List<ExpenseEntity> listExpense = budgetEntity.getExpenseEntities();
		for (ExpenseEntity expenseEntity : listExpense) {
			updateAmount += expenseEntity.getAmount();
		}

		return updateAmount;
	}

	@Override
	public BudgetEntity findTheActiveBudget() {
		AccountEntity accountEntity = userRepository
				.findOneByUserNameAndStatus(SecurityUtils.getPrincipal().getUsername(), SystemConstant.ACTIVE_STATUS);

		BudgetEntity budgetEntity = budgetRepository.findByStatusAndAccountEntity(SystemConstant.ACTIVE_STATUS,
				accountEntity);
		return budgetEntity;
	}

}
