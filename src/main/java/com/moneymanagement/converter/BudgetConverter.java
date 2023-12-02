package com.moneymanagement.converter;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moneymanagement.dto.AccountDTO;
import com.moneymanagement.dto.BudgetDTO;
import com.moneymanagement.dto.ExpenseDTO;
import com.moneymanagement.entity.BudgetEntity;

@Component
public class BudgetConverter {
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ExpenseConverter expenseConverter;

	@Autowired
	private AccountConverter accountConverter;

	public BudgetDTO toDTO(BudgetEntity entity) {

		BudgetDTO dto = new BudgetDTO();
		dto.setId(entity.getId());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCurrentAmount(entity.getCurrentAmount());
		dto.setEndDate(entity.getEndDate());
		dto.setCurrentAmount(entity.getCurrentAmount());
		dto.setStatus(entity.getStatus());
		dto.setAccountDTO(mapper.map(entity.getAccountEntity(), AccountDTO.class));
		dto.setListExpenseDTO(entity.getExpenseEntities().stream()
				.map(expense -> expenseConverter.toDTO(new ExpenseDTO(), expense)).collect(Collectors.toList()));

		return dto;

	}

	public BudgetEntity toEntity(BudgetEntity entity, BudgetDTO dto) {

		entity.setTargetAmount(dto.getTargetAmount());
		entity.setEndDate(dto.getEndDate());

		return entity;
	}

}
