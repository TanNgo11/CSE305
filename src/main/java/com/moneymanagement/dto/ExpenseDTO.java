package com.moneymanagement.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExpenseDTO extends AbstractDTO<ExpenseDTO> {

	private double amount;
	private Date date;
	private String description;
	private AccountDTO accountDTO;
	private CategoryDTO categoryDTO;
}
