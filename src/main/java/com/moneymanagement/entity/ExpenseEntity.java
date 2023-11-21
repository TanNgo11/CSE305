package com.moneymanagement.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
	@JoinColumn(name="accountID")
	private AccountEntity accountEntity;	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="categoryId")
	private CategoryEntity categoryEntity;
	
	
	
	
	
}
