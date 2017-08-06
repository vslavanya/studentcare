package com.rain.urs.ws.soap.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;

import org.apache.cxf.headers.Header;
import org.apache.cxf.jaxb.JAXBDataBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.rain.urs.dao.model.Address;
import com.rain.urs.service.AddressService;
import com.rain.urs.ws.soap.AddressSoapService;
import javax.xml.ws.WebServiceContext;
import javax.annotation.Resource;


public class AddressSoapServiceImpl implements AddressSoapService  {
	
	@PostConstruct
	public void init() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	@Autowired
	private AddressService addressService;


	public List<Address> getAddress(String officeName){

		List<Header> headers = new ArrayList<Header>();
		Header dummyHeader;
		try {
			dummyHeader = new Header(new QName("uri:org.apache.cxf", "dummy"), "decapitated",
			                                new JAXBDataBinding(String.class));
			headers.add(dummyHeader);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		System.out.println("officeName = "+officeName);
		return addressService.getAddress(officeName);
	}


	@Override
	public List<Address> getAddress(String serviceOptions, String officeName) throws Exception {
		System.out.println("officeName = "+serviceOptions);
		return addressService.getAddress(officeName);
	}

}
