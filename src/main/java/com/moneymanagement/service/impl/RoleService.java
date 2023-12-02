package com.moneymanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moneymanagement.converter.RoleConverter;
import com.moneymanagement.dto.RoleDTO;
import com.moneymanagement.repository.RoleRepository;
import com.moneymanagement.service.IRoleService;

@Service
public class RoleService implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private RoleConverter roleConverter;

	@Override
	public RoleDTO findByName(String name) {
		
		return roleConverter.toDTO(roleRepository.findByName(name));
	}
	
	

}
