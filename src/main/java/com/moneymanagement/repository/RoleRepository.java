package com.moneymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moneymanagement.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
	RoleEntity findByName(String name);
}
