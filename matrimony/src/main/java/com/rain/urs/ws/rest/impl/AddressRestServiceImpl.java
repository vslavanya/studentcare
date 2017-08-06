package com.rain.urs.ws.rest.impl;


import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rain.urs.dao.model.Address;
import com.rain.urs.dao.model.AddressResponse;
import com.rain.urs.service.AddressService;
import com.rain.urs.ws.rest.AddressRestService;


public class AddressRestServiceImpl implements AddressRestService {
	
	@Autowired
	private AddressService addressService;
	
	@Context
    private HttpHeaders headers;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressRestServiceImpl.class);

	@GET
    @Path("/v1/address")
    @Produces({"application/xml","application/json"})
	
	
	
	public Response getAddress(@QueryParam("postOffice") String postOffice) {
		List<Address> address = addressService.getPostOffice(postOffice);
		AddressResponse addressResponse = new AddressResponse();
		addressResponse.setAddress(address);
		LOGGER.debug(address.toString());
		System.out.println("header "+ headers.getRequestHeader("name"));
		return Response.ok(addressResponse).build();
		
	}

	

//	@Override
//	@POST
//    @Path("/v2/address")
//	@Consumes({ MediaType.APPLICATION_JSON })
//	@Produces({ MediaType.APPLICATION_JSON })
//	public Response getAddress(LookUpRequest lookUpRequest) {
//		System.out.println(lookUpRequest.toString());
//		List<Address> addressList = new ArrayList<>();
//		
//		addressList = addressService.getPostOffice(lookUpRequest.getPostOffice());
////		if(lookUpRequest.getLookUpType().equals(LookUpType.POSTOFFICE)){
////			 addressList = addressService.getAddress(lookUpRequest.getPostOffice());
////		}
////		else if(lookUpRequest.getLookUpType().equals(LookUpType.PINCODE)){
////			addressList = addressService.getAddressByPinCode(lookUpRequest.getPinCode());
////		}
////		
//		return Response.ok(addressList).build();
//	}

}
