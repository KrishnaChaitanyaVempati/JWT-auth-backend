package com.firstApi.cloudVendor.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.firstApi.cloudVendor.user.model.UserMaster;

public class UserPrincipal implements UserDetails {

	private UserMaster objuser;

	public UserPrincipal(UserMaster objuser) {
		this.objuser = objuser;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return objuser.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return objuser.getUserName();
	}

}
