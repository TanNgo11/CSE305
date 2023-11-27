package com.moneymanagement.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "budget")
public class BudgetEntity extends BaseEntity {
	private Double targetAmount;
	private Double currentAmount;
	private Date endDate;
	private int status;


	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "accountId")
	private AccountEntity accountEntity;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "budgetEntity")
	private List<ExpenseEntity> expenseEntities = new ArrayList<ExpenseEntity>();

}
