package com.moneymanagement.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.moneymanagement.dto.AccountDTO;
import com.moneymanagement.dto.ApiResponse;

public interface IAccountService {
	AccountDTO save(AccountDTO dto);

	AccountDTO findByUsername(String userName);

	AccountDTO findById(long userId);

	List<AccountDTO> getAllAccounts(Pageable pageable);
	
	List<AccountDTO> getAllAccounts();

	ApiResponse disableStatusAccount(long id);

	ApiResponse activeStatusAccount(long id);
	 
	int getTotalItem();
}
