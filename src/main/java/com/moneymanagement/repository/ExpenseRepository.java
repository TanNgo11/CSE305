package com.moneymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moneymanagement.entity.ExpenseEntity;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {

}
