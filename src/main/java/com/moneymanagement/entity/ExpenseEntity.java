package com.moneymanagement.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;


@SuperBuilder(toBuilder = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "expense")
public class ExpenseEntity extends BaseEntity {

	@Column(name = "amount")
	private double amount;

	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountID")
	private AccountEntity accountEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoryId")
	private CategoryEntity categoryEntity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "budgetId")
	private BudgetEntity budgetEntity;

}
