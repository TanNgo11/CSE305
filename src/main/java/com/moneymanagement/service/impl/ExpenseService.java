package com.moneymanagement.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moneymanagement.constant.SystemConstant;
import com.moneymanagement.converter.ExpenseConverter;
import com.moneymanagement.dto.ApiResponse;
import com.moneymanagement.dto.CategoryDTO;
import com.moneymanagement.dto.ExpenseDTO;
import com.moneymanagement.entity.AccountEntity;
import com.moneymanagement.entity.BudgetEntity;
import com.moneymanagement.entity.CategoryEntity;
import com.moneymanagement.entity.ExpenseEntity;
import com.moneymanagement.exception.ResourceNotFoundException;
import com.moneymanagement.exception.UserNotFoundException;
import com.moneymanagement.repository.BudgetRepository;
import com.moneymanagement.repository.CategoryRepository;
import com.moneymanagement.repository.ExpenseRepository;
import com.moneymanagement.repository.UserRepository;
import com.moneymanagement.service.IBudgetService;
import com.moneymanagement.service.IExpenseService;
import com.moneymanagement.utils.SecurityUtils;

@Service
public class ExpenseService implements IExpenseService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ExpenseRepository expenseRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ExpenseConverter expenseConverter;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private IBudgetService budgetService;

	@Autowired
	private BudgetRepository budgetRepository;

	@Override
	public List<ExpenseDTO> getAllExpense() {

		AccountEntity accountEntity = userRepository
				.findOneByUserNameAndStatus(SecurityUtils.getPrincipal().getUsername(), SystemConstant.ACTIVE_STATUS);
		List<ExpenseEntity> lisExpenseEntity = expenseRepository
				.findAllByAccountEntityOrderByCreatedDateDesc(accountEntity);

		List<ExpenseDTO> listExpenseDTO = new ArrayList<>();

		for (ExpenseEntity expenseEntity : lisExpenseEntity) {
			CategoryDTO cateDTO = mapper.map(expenseEntity.getCategoryEntity(), CategoryDTO.class);
			ExpenseDTO expenseDTO = mapper.map(expenseEntity, ExpenseDTO.class);
			expenseDTO.setCategoryDTO(cateDTO);

			listExpenseDTO.add(expenseDTO);
		}
		return listExpenseDTO;
	}

	@Override
	public ExpenseDTO getExpenseByID(long id) {
		ExpenseEntity expenseEntity = expenseRepository.findOne(id);
		if (expenseEntity == null) {
			throw new ResourceNotFoundException("Expense not exists!");
		}
		CategoryEntity cateEntity = expenseEntity.getCategoryEntity();

		ExpenseDTO expenseDTO = mapper.map(expenseRepository.getOne(id), ExpenseDTO.class);
		expenseDTO.setCategoryDTO(mapper.map(cateEntity, CategoryDTO.class));

		return expenseDTO;
	}

	@Override
	@Transactional
	public ExpenseDTO saveExpenseDTO(ExpenseDTO expenseDTO, long cateId) {
		if (SecurityUtils.getPrincipal() == null) {
			throw new UserNotFoundException("Not found User");
		}

		AccountEntity accountEntity = userRepository
				.findOneByUserNameAndStatus(SecurityUtils.getPrincipal().getUsername(), SystemConstant.ACTIVE_STATUS);

		CategoryEntity cateEntity = categoryRepository.findOne(cateId);
		ExpenseEntity expenseEntity = mapper.map(expenseDTO, ExpenseEntity.class);
		expenseEntity.setCategoryEntity(cateEntity);
		expenseEntity.setAccountEntity(accountEntity);

		BudgetEntity budgetEntity = budgetService.findTheActiveBudget();

		if (budgetEntity == null)
			throw new ResourceNotFoundException("Not Found current Budget");

		budgetEntity.setCurrentAmount(budgetEntity.getCurrentAmount() + expenseDTO.getAmount());

		expenseEntity.setBudgetEntity(budgetEntity);

		expenseRepository.save(expenseEntity);

		return mapper.map(expenseEntity, ExpenseDTO.class);
	}

	@Override
	public ApiResponse deleteExpenseByID(long id) {
		ExpenseEntity expenseEntity = expenseRepository.findOne(id);
		if (expenseEntity == null) {
			throw new ResourceNotFoundException("Expense not exists!");
		}

		expenseRepository.delete(expenseEntity);

		BudgetEntity budgetEntity = budgetService.findTheActiveBudget();
		budgetEntity.setCurrentAmount(budgetService.updateCurrentTarget());

		budgetRepository.save(budgetEntity);

		ApiResponse respone = new ApiResponse();
		respone.setHttp(HttpStatus.ACCEPTED);
		respone.setMessage("Delete successfull!");
		respone.setSuccess(true);
		return respone;
	}

	@Override
	public ExpenseDTO editExpenseDTO(ExpenseDTO expenseDTO) {

		ExpenseEntity expenseEntity = expenseRepository.findOne(expenseDTO.getId());

		if (expenseEntity == null) {
			throw new ResourceNotFoundException("Expense not exists!");
		}
		expenseEntity = expenseConverter.toEntity(expenseEntity, expenseDTO);

		expenseRepository.save(expenseEntity);

		return mapper.map(expenseEntity, ExpenseDTO.class);
	}

	@Override
	public List<ExpenseDTO> findAllExpensesByMonthAndYear(int month, int year) {
		AccountEntity accountEntity = userRepository
				.findOneByUserNameAndStatus(SecurityUtils.getPrincipal().getUsername(), SystemConstant.ACTIVE_STATUS);
		List<ExpenseEntity> listExpenseEntity = expenseRepository
				.findAllExpensesByMonthAndYear(month, year, accountEntity)
				.orElseThrow(() -> new ResourceNotFoundException("Can not found any expense"));

		return listExpenseEntity.stream().map(expense -> expenseConverter.toDTO(new ExpenseDTO(), expense))
				.collect(Collectors.toList());

	}

	@Override
	public int getTotalExpense() {

		return (int) expenseRepository.count();
	}

}
