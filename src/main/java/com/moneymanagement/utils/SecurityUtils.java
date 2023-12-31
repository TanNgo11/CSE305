package com.moneymanagement.utils;



import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.moneymanagement.dto.MyUser;

public class SecurityUtils {

	public static MyUser getPrincipal() {

		MyUser myUser = (MyUser) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
		return myUser;

	}

	@SuppressWarnings("unchecked")
	public static List<String> getAuthorities() {
		List<String> results = new ArrayList<String>();

		List<GrantedAuthority> authorities = (List<GrantedAuthority>) (SecurityContextHolder.getContext()
				.getAuthentication().getAuthorities());
		for (GrantedAuthority authority : authorities) {
			results.add(authority.getAuthority());

		}
		return results;
	}

	public static boolean checkCurrentID(long id) {
		
		if (getPrincipal().getId() != id)
			return false;
		return true;

	}
}
