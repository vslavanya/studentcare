package com.rain.urs.service;

import java.util.List;

import com.rain.urs.dao.model.Address;

public interface AddressService {
	public List<Address> getAddress(String postOfficeName);
	
//	public String getPincode(String postOfficeName);

	public List<Address> getAddressByPinCode(String pinCode);
	
	public List<Address> getPostOffice(String searchKey);

}
