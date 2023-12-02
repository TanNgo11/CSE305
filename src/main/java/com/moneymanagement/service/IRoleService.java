package com.moneymanagement.service;

import com.moneymanagement.dto.RoleDTO;

public interface IRoleService {
	RoleDTO findByName(String name);
}
