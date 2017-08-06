package com.rain.urs.service.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rain.urs.dao.AddressDao;
import com.rain.urs.dao.model.Address;
import com.rain.urs.dao.model.request.LookUpType;
import com.rain.urs.service.AddressService;

public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressDao addressDao;

	@Override
	@Transactional
	public List<Address> getAddress(String postOfficeName) {

		List<Address> addressList = addressDao.findAddress(postOfficeName);
		// System.out.println(address.toString());
		return addressList;

	}

	// @Override
	// public String getPincode(String postOfficeName) {
	// List<Address> addressList = addressDao.findAddress(postOfficeName);
	// for (Address address : addressList) {
	// if (address.getOfficename().equals(postOfficeName)) {
	// return address.getPincode();
	// }
	// }
	// return null;
	// }

	@Override
	@Transactional
	public List<Address> getAddressByPinCode(String pinCode) {
		List<Address> addressList = addressDao.findAddressByPinCode(pinCode);
		return addressList;
	}


	@Override
	@Transactional
	public List<Address> getPostOffice(String searchKey) {
		LookUpType lookUpType = getLookUpType(searchKey);
		System.out.println("lookUpType ="+lookUpType);
		switch(lookUpType){
			case PINCODE :
				return getAddressByPinCode(searchKey);
		case POSTOFFICE :
				return getAddress(searchKey);
		default:
			return null;
				
		}
		
	}

	private LookUpType getLookUpType(String searchKey) {

		if (Pattern.matches("[a-zA-Z]+", searchKey) == false && searchKey.length() > 2) {
			return LookUpType.PINCODE;
		} else if (Pattern.matches("[0-9]+", searchKey) == false && searchKey.length() > 2) {
			return LookUpType.POSTOFFICE;
		}
		return LookUpType.INVALID;
	}

}
