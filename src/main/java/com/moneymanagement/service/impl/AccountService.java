package com.moneymanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moneymanagement.constant.SystemConstant;
import com.moneymanagement.converter.AccountConverter;
import com.moneymanagement.dto.AccountDTO;
import com.moneymanagement.dto.ApiResponse;
import com.moneymanagement.entity.AccountEntity;
import com.moneymanagement.entity.RoleEntity;
import com.moneymanagement.exception.UserNotFoundException;
import com.moneymanagement.repository.RoleRepository;
import com.moneymanagement.repository.UserRepository;
import com.moneymanagement.service.IAccountService;

@Service
public class AccountService implements IAccountService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AccountConverter accountConverter;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder BCryptPasswordEncoder;

	@Override
	@Transactional
	public AccountDTO save(AccountDTO dto) {
		AccountEntity accountEntity = userRepository.findOneByUserNameAndStatus(dto.getUsername(),
				SystemConstant.ACTIVE_STATUS);
		if (accountEntity == null) {
			accountEntity = accountConverter.toEntity(dto);
			List<RoleEntity> roles = new ArrayList<RoleEntity>();
			roles.add(roleRepository.findByName("USER"));
			accountEntity.setRoles(roles);
			accountEntity.setPassword(BCryptPasswordEncoder.encode(dto.getPassword()));
			return accountConverter.toDTO(userRepository.save(accountEntity));
		}
		return null;
	}

	@Override
	public AccountDTO findByUsername(String userName) {
		AccountEntity entity = userRepository.findOneByUserNameAndStatus(userName, SystemConstant.ACTIVE_STATUS);
		AccountDTO dto = accountConverter.toDTO(entity);
		return dto;
	}

	@Override
	public AccountDTO findById(long userId) {

		return accountConverter.toDTO(userRepository.findOne(userId));
	}

	@Override
	public ApiResponse disableStatusAccount(long id) {
		AccountEntity account = userRepository.findOne(id);
		if (account == null) {
			throw new UserNotFoundException("Not found user!");
		}
		account.setStatus(SystemConstant.INACTIVE_STATUS);
		userRepository.save(account);
		return ApiResponse.builder()
				.http(HttpStatus.OK)
				.message("Disable Account Successfull")
				.success(true)
				.build();
	}

	@Override
	public ApiResponse activeStatusAccount(long id) {
		AccountEntity account = userRepository.findOne(id);
		if (account == null) {
			throw new UserNotFoundException("Not found user!");
		}
		account.setStatus(SystemConstant.ACTIVE_STATUS);
		userRepository.save(account);
		
		return ApiResponse.builder()
					.http(HttpStatus.OK)
					.message("Active Account Successfull")
					.success(true)
					.build();
		
	}

	@Override
	public List<AccountDTO> getAllAccounts(org.springframework.data.domain.Pageable pageable) {
		List<AccountEntity> listAccountEntity = userRepository.findAll(pageable).getContent();

		List<AccountDTO> listAccountDTO = new ArrayList<>();

		for (AccountEntity accountEntity : listAccountEntity) {
			listAccountDTO.add(accountConverter.toDTO(accountEntity));
		}

		return listAccountDTO;
	}

	@Override
	public int getTotalItem() {
		return (int) userRepository.count();
		
	}

	@Override
	public List<AccountDTO> getAllAccounts() {
		List<AccountEntity> listAccountEntity = userRepository.findAll();

		List<AccountDTO> listAccountDTO = new ArrayList<>();

		for (AccountEntity accountEntity : listAccountEntity) {
			listAccountDTO.add(accountConverter.toDTO(accountEntity));
		}

		return listAccountDTO;
		
	}
}
