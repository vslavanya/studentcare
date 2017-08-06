package com.rain.urs.ws.soap;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;


import com.rain.urs.dao.model.Address;
@WebService(endpointInterface = "com.rain.urs.ws.soap.AddressSoapService", serviceName = "addressSoapService")
public interface AddressSoapService {
	public List<Address> getAddress(
			 @WebParam(name="serviceOptions", 
             header=true) String serviceOptions,
			 @WebParam(name = "officeName") String officeName) throws Exception;
	
}
