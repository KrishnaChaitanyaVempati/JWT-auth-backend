package com.firstApi.cloudVendor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.firstApi.cloudVendor.dao.UserRepo;
import com.firstApi.cloudVendor.user.model.UserMaster;

@Service("CustomerUserDetailsService")
public class CustomerUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepo objUserRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserMaster objUserMaster = objUserRepo.findByUserName(username);
		if(objUserMaster==null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new UserPrincipal(objUserMaster);
	}

}
