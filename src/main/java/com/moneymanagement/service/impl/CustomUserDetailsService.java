package com.moneymanagement.service.impl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.moneymanagement.constant.SystemConstant;
import com.moneymanagement.dto.MyUser;
import com.moneymanagement.entity.AccountEntity;
import com.moneymanagement.entity.RoleEntity;
import com.moneymanagement.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AccountEntity userEntity = userRepository.findOneByUserNameAndStatus(username, SystemConstant.ACTIVE_STATUS);
		

		if (userEntity == null) {
			return null;
			
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (RoleEntity role : userEntity.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		MyUser myUser = new MyUser(userEntity.getId(), userEntity.getFullName(),username, userEntity.getPassword(), true, true, true, true,
				authorities);
		
	
		return myUser;
	}

}