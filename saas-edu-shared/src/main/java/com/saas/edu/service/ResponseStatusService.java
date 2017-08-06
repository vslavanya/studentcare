package com.saas.edu.service;

import com.saas.edu.dto.StatusDto;

public interface ResponseStatusService {

	public StatusDto getStatusDto(Exception e) ;
	public StatusDto getStatusDto() ;
}
