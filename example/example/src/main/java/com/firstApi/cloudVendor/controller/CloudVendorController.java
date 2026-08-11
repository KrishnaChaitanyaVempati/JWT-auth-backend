package com.firstApi.cloudVendor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.firstApi.cloudVendor.model.CloudVendorModel;
import com.firstApi.cloudVendor.service.CloudVendorService;
import com.firstApi.cloudVendor.service.UserRegisterService;
import com.firstApi.cloudVendor.user.model.UserMaster;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cloudVendor")
//@CrossOrigin(origins = "http://localhost:4200")
public class CloudVendorController {

	@Autowired
	CloudVendorService objCloudVendorService;
	
	@Autowired
	UserRegisterService objUserRegisterService;

	@GetMapping("/vendors/{vendorId}")
	public CloudVendorModel getVendorDetails(@PathVariable("vendorId") String id) {
//		return new CloudVendorModel(id, "Vendor-1", "Vijayawada", "97015252116");
		return objCloudVendorService.getVendorById(id);
	}

	@GetMapping("/getVendors")
	public List<CloudVendorModel> getAllVendorDetails() {
//		return new CloudVendorModel(id, "Vendor-1", "Vijayawada", "97015252116");
		return objCloudVendorService.getVendors();
	}

	@PostMapping("/addVendorDetails")
	public String insertVendorDetails(@RequestBody CloudVendorModel objCloudVendor) {

		CloudVendorModel objModel = objCloudVendorService.addVendorDetails(objCloudVendor);
		String message = "Cloud Vendor details created successfully";
		if (objModel != null)
			return objModel.getVendorId() + " " + message;
		else {
			return "Error which adding Cloud Vendor details";
		}

	}

	@PutMapping("/updateVendorDetails")
	public String putVendorDetails(@RequestBody CloudVendorModel objCloudVendor) {

		CloudVendorModel objModel = objCloudVendorService.updateVendorDetails(objCloudVendor);
		String message = "Cloud Vendor details updated successfully";
		if (objModel != null)
			return objModel.getVendorId() + " " + message;
		else {
			return "Cloud Vendor details not found";
		}

	}

	@DeleteMapping("/deleteVendorDetails/{vendorId}")
	public String deleteVendorDetails(@PathVariable("vendorId") String id) {

		boolean isRemoved = objCloudVendorService.deleteVendorDetails(id);
		String message = "Cloud Vendor details deleted successfully";
		
		if (isRemoved)
			return id + " " + message;
		else {
			return "Cloud Vendor details not found";
		}

	}
	
	@GetMapping("/getCsrfToken")
	public CsrfToken getCsrfToken(HttpServletRequest request) {
		
		return (CsrfToken) request.getAttribute("_csrf");
		
	}
	
	@PostMapping("/registerUser")
	public UserMaster registerUser(@RequestBody UserMaster request) {
		
		return objUserRegisterService.registerUser(request);
		
	}
	
	@PostMapping("/loginUser")
	public String loginUser(@RequestBody UserMaster request) {
		
		return objUserRegisterService.loginUser(request);
		
	}

}
