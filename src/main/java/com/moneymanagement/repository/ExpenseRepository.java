package com.moneymanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.moneymanagement.entity.ExpenseEntity;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {
	@Query("SELECT o FROM ExpenseEntity o " + "WHERE FUNCTION('MONTH', o.createdDate) = :month "
			+ "AND FUNCTION('YEAR', o.createdDate) = :year")
	Optional<List<ExpenseEntity>> findAllExpensesByMonthAndYear(@Param("month") int month, @Param("year") int year,
			Pageable pageable);

}
