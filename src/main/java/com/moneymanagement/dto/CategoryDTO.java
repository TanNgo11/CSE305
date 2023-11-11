package com.moneymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDTO extends AbstractDTO<CategoryDTO>{

	private String name;
	private ExpenseDTO expenseDTO;
	
}
