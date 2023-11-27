package com.moneymanagement.service;

import com.moneymanagement.dto.AccountDTO;

public interface IAccountService {
	AccountDTO save(AccountDTO dto);
	AccountDTO findByUsername(String userName);
	AccountDTO findById(long userId);
}
