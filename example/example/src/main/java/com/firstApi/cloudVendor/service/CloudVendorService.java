package com.firstApi.cloudVendor.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.firstApi.cloudVendor.dao.VendorRepo;
import com.firstApi.cloudVendor.model.CloudVendorModel;

@Service
public class CloudVendorService {

	@Autowired
	VendorRepo objVendorRepo;

	// Arrays.asList returns an immutable list - cant perform size changing
	// operations
//	List<CloudVendorModel> objList = new ArrayList<>(
//			Arrays.asList(new CloudVendorModel("101", "Vendor-1", "Vijayawada", "9701525211"),
//					new CloudVendorModel("102", "Vendor-2", "Hyderabad", "8801535211"),
//					new CloudVendorModel("103", "Vendor-3", "Benguluru", "7262626622")));

	public List<CloudVendorModel> getVendors() {
		return objVendorRepo.findAll();
	}

	public CloudVendorModel getVendorById(String id) {
		// return objList.stream().filter(vendor -> vendor.getVendorId() ==
		// id).findFirst().orElseThrow();

		return objVendorRepo.findById(id).orElse(new CloudVendorModel());
	}

	/*
	 * public CloudVendorModel addVendorDetails(CloudVendorModel objCloudVendor) {
	 * boolean isAdded = objList.add(objCloudVendor); if (isAdded) { return
	 * objCloudVendor; } return null; }
	 */

	public CloudVendorModel addVendorDetails(CloudVendorModel objCloudVendor) {
		return objVendorRepo.save(objCloudVendor);
	}

	/*
	 * public CloudVendorModel updateVendorDetails(CloudVendorModel objCloudVendor)
	 * {
	 * 
	 * int index = 0; for (int i = 0; i < objList.size(); i++) { if
	 * (objList.get(i).getVendorId().equalsIgnoreCase(objCloudVendor.getVendorId()))
	 * { index = i; } }
	 * 
	 * objList.set(index, objCloudVendor); return objList.get(index); }
	 */

	public CloudVendorModel updateVendorDetails(CloudVendorModel objCloudVendor) {

		return objVendorRepo.save(objCloudVendor);
	}

	/*
	 * public boolean deleteVendorDetails(String id) {
	 * 
	 * int index = -1; for (int i = 0; i < objList.size(); i++) { if
	 * (objList.get(i).getVendorId().equalsIgnoreCase(id)) { index = i; } }
	 * 
	 * if (index != -1) { objList.remove(index); return true; } else { return false;
	 * } }
	 */

	public boolean deleteVendorDetails(String id) {
		try {
			objVendorRepo.deleteById(id);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
