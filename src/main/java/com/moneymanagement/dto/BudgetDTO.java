package com.moneymanagement.dto;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BudgetDTO extends AbstractDTO<BudgetDTO> {

	private Double targetAmount;
	private Double currentAmount;
	private Date endDate;
	private int status;
	private AccountDTO accountDTO;
	private List<ExpenseDTO> listExpenseDTO;

}
