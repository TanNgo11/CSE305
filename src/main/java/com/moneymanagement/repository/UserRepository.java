package com.moneymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moneymanagement.entity.AccountEntity;

public interface UserRepository extends JpaRepository<AccountEntity, Long> {
	AccountEntity findOneByUserNameAndStatus(String name, int status);
	
	
}
