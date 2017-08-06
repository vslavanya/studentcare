package com.saas.edu.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.saas.edu.dto.StatusDto;
import com.saas.edu.service.ResponseStatusService;

@Service
public class ResponseStatusServiceImpl implements ResponseStatusService {

	@Override
	public StatusDto getStatusDto(Exception e) {
		StatusDto statusDto = new StatusDto();
		statusDto.setDate(new Date().toString());
		statusDto.setErrorCode(9999);
		statusDto.setException(e.getClass().getSimpleName());
		statusDto.setStatus(false);
		return statusDto;
	}

	@Override
	public StatusDto getStatusDto() {
		StatusDto statusDto = new StatusDto();
		statusDto.setDate(new Date().toString());
		statusDto.setStatus(true);
		return statusDto;
	}

}
