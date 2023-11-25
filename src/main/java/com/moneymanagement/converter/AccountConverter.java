package com.moneymanagement.converter;

import org.springframework.stereotype.Component;

import com.moneymanagement.constant.SystemConstant;
import com.moneymanagement.dto.AccountDTO;
import com.moneymanagement.entity.AccountEntity;

@Component
public class AccountConverter {

	public AccountDTO toDTO(AccountEntity accountEntity) {

		AccountDTO account = new AccountDTO();
		account.setId(accountEntity.getId());
		account.setFullName(accountEntity.getFullName());
		account.setUsername(accountEntity.getUserName());
		account.setPhoneNumber(accountEntity.getPhoneNumber());
		account.setAddress(accountEntity.getAddress());
		account.setEmail(accountEntity.getEmail());
		account.setStatus(accountEntity.getStatus());
		account.setListRole(accountEntity.getRoles());

		if (accountEntity.getCreatedBy() != null) {
			account.setCreatedBy(accountEntity.getCreatedBy());
		}
		if (accountEntity.getCreatedDate() != null) {
			account.setCreatedDate(accountEntity.getCreatedDate());
		}
		if (accountEntity.getModifiedBy() != null) {
			account.setModifiedBy(accountEntity.getModifiedBy());
		}
		if (accountEntity.getModifiedDate() != null) {
			account.setModifiedDate(accountEntity.getModifiedDate());
		}
		return account;
	}

	public AccountEntity toEntity(AccountDTO dto) {

		AccountEntity account = new AccountEntity();
		account.setFullName(dto.getFullName());
		account.setUserName(dto.getUsername());
		account.setAddress(dto.getAddress());
		account.setEmail(dto.getEmail());
		account.setStatus(SystemConstant.ACTIVE_STATUS);
		return account;
	}

	public AccountEntity toEntity(AccountEntity result, AccountDTO dto) {

		result.setPhoneNumber(dto.getPhoneNumber());
		result.setAddress(dto.getAddress());
		result.setEmail(dto.getEmail());
		return result;
	}

}
