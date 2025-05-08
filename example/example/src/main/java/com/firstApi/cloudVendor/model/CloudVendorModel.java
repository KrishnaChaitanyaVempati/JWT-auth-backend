package com.firstApi.cloudVendor.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
//@Data

@Entity
@Table(name="cloud_vendor")
public class CloudVendorModel {

	@Id
	@Column(name="vendor_id")
	private String vendorId;
	
	@Column(name="vendor_name")
	private String vendorName;
	
	@Column(name="vendor_address")
	private String vendorAddress;
	
	@Column(name="phone_number")
	private String phoneNumber;

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorAddress() {
		return vendorAddress;
	}

	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "CloudVendorModel [vendorId=" + vendorId + ", vendorName=" + vendorName + ", vendorAddress="
				+ vendorAddress + ", phoneNumber=" + phoneNumber + "]";
	}

}
