package com.moneymanagement.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CategoryDTO extends AbstractDTO<CategoryDTO>{

	
	private String name;
	private int status;
	@JsonIgnore
	private List<ExpenseDTO> listExpenseDTO ;
	
}
