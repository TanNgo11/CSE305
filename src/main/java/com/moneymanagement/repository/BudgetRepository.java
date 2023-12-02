package com.moneymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moneymanagement.entity.AccountEntity;
import com.moneymanagement.entity.BudgetEntity;

public interface BudgetRepository extends JpaRepository<BudgetEntity, Long>{
	
	BudgetEntity findByStatusAndAccountEntity(int status, AccountEntity accountEntity);

}
