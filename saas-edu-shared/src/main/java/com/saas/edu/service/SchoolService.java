package com.saas.edu.service;

import java.util.List;

import com.saas.edu.dto.SchoolDto;

public interface SchoolService {
	public void addSchool(SchoolDto schoolDto);
	public List<SchoolDto> findByPinCode(String pin);
}
