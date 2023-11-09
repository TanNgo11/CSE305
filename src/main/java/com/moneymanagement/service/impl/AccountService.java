package com.moneymanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moneymanagement.constant.SystemConstant;
import com.moneymanagement.converter.AccountConverter;
import com.moneymanagement.dto.AccountDTO;
import com.moneymanagement.entity.AccountEntity;
import com.moneymanagement.entity.RoleEntity;
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

}
