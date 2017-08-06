package com.rain.urs.ws.rest;

import javax.ws.rs.core.Response;

import com.rain.urs.dao.model.request.LookUpRequest;

public interface AddressRestService {
	public Response getAddress(String postOffice);

}
