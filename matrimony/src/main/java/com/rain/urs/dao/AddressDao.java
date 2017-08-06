package com.rain.urs.dao;

import java.util.List;

import com.rain.urs.dao.model.Address;

public interface AddressDao extends GenericDao<Address> {
	
	public List<Address> findAddress(String officeName);
	public List<Address> findAddressByPinCode(String pinCode);

}
