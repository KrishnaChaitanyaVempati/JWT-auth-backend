package com.firstApi.cloudVendor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.firstApi.cloudVendor.dao.UserRepo;
import com.firstApi.cloudVendor.user.model.UserMaster;

@Service
public class UserRegisterService {

	@Autowired
	UserRepo objUserRepo;

	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtService jwtService;

	private BCryptPasswordEncoder objBCryptPasswordEncoder = new BCryptPasswordEncoder(15);

	public UserMaster registerUser(UserMaster objUser) {

		objUser.setPassword(objBCryptPasswordEncoder.encode(objUser.getPassword()));
		return objUserRepo.save(objUser);
	}

	public String loginUser(UserMaster request) {
		Authentication authentication = authManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

		if (authentication.isAuthenticated()) {
			// return "User Logged in successfully.";
			return jwtService.generateToken(request);
		}
		
		throw new UsernameNotFoundException("Invalid User details");
	}

}
