package com.moneymanagement.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "expense")
public class ExpenseEntity extends BaseEntity {

	@Column(name = "amount")
	private double amount;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "description")
	private String description;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="accountID")
	private AccountEntity accountEntity;	
	
	@OneToMany(mappedBy = "expensesEntity")
	private List<CategoryEntity> entities = new ArrayList<CategoryEntity>();
	
	
	
}
