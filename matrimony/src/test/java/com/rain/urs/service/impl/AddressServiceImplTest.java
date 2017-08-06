package com.rain.urs.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rain.urs.dao.AddressDao;
import com.rain.urs.dao.model.Address;
import com.rain.urs.service.AddressService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext-spring-test.xml" })

public class AddressServiceImplTest {

	 List<Address> addressList;
	 
	 @Autowired
	 private AddressService addressService;
	 AddressDao addressDao;
	
//	@BeforeClass
//	public static void setUp() {
//		 addressDao = Mockito.mock(AddressDao.class);
//		addressService = Mockito.mock(AddressService.class);
//
//		Address newAddress = new Address();
//
//		newAddress.setPincode("691312");
//		 addressList = new ArrayList<>();
//		addressList.add(newAddress);
//
//		when(addressDao.findAddress("bha")).thenReturn(addressList);
//		
//	}

		

	@Test
	public void getAddressTest() throws Exception {
		//assertNotNull(addressList);
		addressService.getAddress("bha");
		 
	}
	

	@Test
	public void getAddressAnotherTest() throws Exception {
		//assertNotNull(addressList);
		addressService.getAddress("KUM");
		
	}
	
	

}
