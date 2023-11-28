package com.moneymanagement.service.impl;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymanagement.constant.SystemConstant;
import com.moneymanagement.entity.AccountEntity;
import com.moneymanagement.entity.CategoryEntity;
import com.moneymanagement.entity.ExpenseEntity;
import com.moneymanagement.exception.ResourceNotFoundException;
import com.moneymanagement.repository.CategoryRepository;
import com.moneymanagement.repository.ExpenseRepository;
import com.moneymanagement.repository.UserRepository;
import com.moneymanagement.service.ICanvasjsChartService;
import com.moneymanagement.utils.SecurityUtils;

@Service
public class CanvasjsChartService implements ICanvasjsChartService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<List<Map<Object, Object>>> getCanvasjsPieChartData(String type) {
		Map<Object, Object> map = null;
		List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
		List<Map<Object, Object>> dataPoints1 = new ArrayList<Map<Object, Object>>();

		List<CategoryEntity> listCategories = categoryRepository.findAll();
		Map<CategoryEntity, Double> mapExpense = calculatePercentByCurrentTime(listCategories, type);

		for (Map.Entry<CategoryEntity, Double> entry : mapExpense.entrySet()) {
			CategoryEntity category = entry.getKey();
			Double percentage = entry.getValue();
			map = new HashMap<Object, Object>();
			map.put("label", category.getName());
			map.put("y", percentage);
			dataPoints1.add(map);

		}

		list.add(dataPoints1);

		return list;
	}

	@Override
	public List<List<Map<Object, Object>>> getCanvasjsColumnChartData(int year) {
		Map<Object, Object> map = null;
		List<List<Map<Object, Object>>> list = new ArrayList<List<Map<Object, Object>>>();
		List<Map<Object, Object>> dataPoints1 = new ArrayList<Map<Object, Object>>();

		Map<Long, Double> mapExpense = getMonthlyAmountInOneYear(year);

		for (Entry<Long, Double> entry : mapExpense.entrySet()) {
			map = new HashMap<Object, Object>();
			map.put("x", entry.getKey());
			map.put("y", entry.getValue());
			dataPoints1.add(map);
			dataPoints1.add(map);

		}

		list.add(dataPoints1);

		return list;

	}

	private Map<CategoryEntity, Double> calculatePercentByCurrentTime(List<CategoryEntity> listCategories,
			String type) {

		AccountEntity accountEntity = userRepository
				.findOneByUserNameAndStatus(SecurityUtils.getPrincipal().getUsername(), SystemConstant.ACTIVE_STATUS);
		List<ExpenseEntity> listExpense;
		switch (type) {
		case "week":
			listExpense = expenseRepository.findAllExpensesForCurrentWeekByAccount(accountEntity);
			break;

		case "month":
			listExpense = expenseRepository.findAllExpensesForCurrentMonthByAccount(accountEntity);
			break;

		case "year":
			listExpense = expenseRepository.findAllExpensesForCurrentYearByAccount(accountEntity);
			break;

		default:
			listExpense = new ArrayList<>();
			break;
		}

		Double sum = totalAmount(listExpense);
		Map<CategoryEntity, Double> map = new HashMap<>();
		for (ExpenseEntity expenseEntity : listExpense) {
			Double oldValue = map.get(expenseEntity);
			Double currentPercent = expenseEntity.getAmount() / sum * 100;
			map.put(expenseEntity.getCategoryEntity(),
					oldValue == null ? oldValue = currentPercent : currentPercent + oldValue);

		}

		return map;

	}

	private double totalAmount(List<ExpenseEntity> listExpense) {
		Double sum = 0.0;
		for (ExpenseEntity expenseEntity : listExpense) {
			sum += expenseEntity.getAmount();
		}
		return sum;
	}

	private Map<CategoryEntity, Double> calculatePercentByMonthAndYear(List<CategoryEntity> listCategories, int month,
			int year) {

		AccountEntity accountEntity = userRepository
				.findOneByUserNameAndStatus(SecurityUtils.getPrincipal().getUsername(), SystemConstant.ACTIVE_STATUS);
		List<ExpenseEntity> listExpenseEntity = expenseRepository
				.findAllExpensesByMonthAndYear(month, year, accountEntity)
				.orElseThrow(() -> new ResourceNotFoundException("Can not found any expense"));

		Double sum = totalAmount(listExpenseEntity);
		Map<CategoryEntity, Double> map = new HashMap<>();
		for (ExpenseEntity expenseEntity : listExpenseEntity) {
			Double oldValue = map.get(expenseEntity);
			Double currentPercent = expenseEntity.getAmount() / sum * 100;
			map.put(expenseEntity.getCategoryEntity(),
					oldValue == null ? oldValue = currentPercent : currentPercent + oldValue);

		}

		return map;

	}

	private Map<Long, Double> getMonthlyAmountInOneYear(int year) {

		Map<Long, Double> map = new HashMap<>();
		AccountEntity accountEntity = userRepository
				.findOneByUserNameAndStatus(SecurityUtils.getPrincipal().getUsername(), SystemConstant.ACTIVE_STATUS);
		List<ExpenseEntity> listExpense = expenseRepository.findAllExpensesByAccountAndYear(accountEntity, year);

		for (ExpenseEntity expenseEntity : listExpense) {
			long timestamp = convertMonthToTimestamp(year, extractMonthFromDate(expenseEntity.getCreatedDate()));

			map.put(timestamp, map.get(timestamp) == null ? expenseEntity.getAmount()
					: map.get(timestamp) + expenseEntity.getAmount());
		}

		return map;
	}

	public static int extractMonthFromDate(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		return calendar.get(Calendar.MONTH) + 1;
	}

	public static long convertMonthToTimestamp(int year, int month) {

		LocalDate firstDayOfMonth = LocalDate.of(year, month, 1);

		Instant instant = firstDayOfMonth.atStartOfDay().atZone(ZoneOffset.UTC).toInstant();

		return instant.toEpochMilli();
	}

}
