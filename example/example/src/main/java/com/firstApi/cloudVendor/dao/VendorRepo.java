package com.firstApi.cloudVendor.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.firstApi.cloudVendor.model.CloudVendorModel;

@Repository
public interface VendorRepo extends JpaRepository<CloudVendorModel, String> {

}
