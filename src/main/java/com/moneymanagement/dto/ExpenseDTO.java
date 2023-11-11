package com.moneymanagement.dto;

import java.util.ArrayList;
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
public class ExpenseDTO {
	private double amount;
	private Date date;
	private String description;
	private AccountDTO accountDTO;
	private List<CategoryDTO> cateEntities = new ArrayList<CategoryDTO>();
}
