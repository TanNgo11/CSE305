package com.moneymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moneymanagement.entity.BudgetEntity;

public interface BudgetRepository extends JpaRepository<BudgetEntity, Long>{

}
