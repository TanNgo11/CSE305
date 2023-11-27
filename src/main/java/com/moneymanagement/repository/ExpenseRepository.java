package com.moneymanagement.repository;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.moneymanagement.entity.AccountEntity;
import com.moneymanagement.entity.ExpenseEntity;

public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {
	 @Query("SELECT o FROM ExpenseEntity o " +
	            "WHERE FUNCTION('MONTH', o.createdDate) = :month " +
	            "AND FUNCTION('YEAR', o.createdDate) = :year " +
	            "AND o.accountEntity = :accountEntity")
	    Optional<List<ExpenseEntity>> findAllExpensesByMonthAndYear(
	            @Param("month") int month,
	            @Param("year") int year,
	            @Param("accountEntity") AccountEntity accountEntity);

	@Query("SELECT e FROM ExpenseEntity e " +
            "WHERE WEEK(e.createdDate, 3) = WEEK(CURRENT_DATE, 3) " +
            "AND YEAR(e.createdDate) = YEAR(CURRENT_DATE) " +
            "AND e.accountEntity = :accountEntity")
    List<ExpenseEntity> findAllExpensesForCurrentWeekByAccount(@Param("accountEntity") AccountEntity accountEntity);

    @Query("SELECT e FROM ExpenseEntity e " +
            "WHERE MONTH(e.createdDate) = MONTH(CURRENT_DATE) " +
            "AND YEAR(e.createdDate) = YEAR(CURRENT_DATE) " +
            "AND e.accountEntity = :accountEntity")
    List<ExpenseEntity> findAllExpensesForCurrentMonthByAccount(@Param("accountEntity") AccountEntity accountEntity);

    @Query("SELECT e FROM ExpenseEntity e " +
            "WHERE YEAR(e.createdDate) = YEAR(CURRENT_DATE) " +
            "AND e.accountEntity = :accountEntity")
    List<ExpenseEntity> findAllExpensesForCurrentYearByAccount(@Param("accountEntity") AccountEntity accountEntity);
    
    @Query("SELECT e FROM ExpenseEntity e " +
            "WHERE YEAR(e.createdDate) = :year " +
            "AND e.accountEntity = :accountEntity")
    List<ExpenseEntity> findAllExpensesByAccountAndYear(
            @Param("accountEntity") AccountEntity accountEntity,
            @Param("year") int year);

	List<ExpenseEntity> findAllByAccountEntityOrderByCreatedDateDesc(AccountEntity accountEntity);

}
