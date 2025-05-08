package com.firstApi.cloudVendor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.firstApi.cloudVendor.user.model.UserMaster;

@Repository
public interface UserRepo extends JpaRepository<UserMaster, Integer> {

	UserMaster findByUserName(String username);

}
