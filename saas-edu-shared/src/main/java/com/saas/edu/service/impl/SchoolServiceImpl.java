package com.saas.edu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.edu.dao.model.AbstractAuditEntity;
import com.saas.edu.dao.model.Sbjct;
import com.saas.edu.dao.model.Schl;
import com.saas.edu.dao.model.SchlCtctDet;
import com.saas.edu.dao.model.SchlSbjctMap;
import com.saas.edu.dao.repository.SchoolContactDetailsRepository;
import com.saas.edu.dao.repository.SchoolRepository;
import com.saas.edu.dao.repository.SchoolSubjectMapRepository;
import com.saas.edu.dao.repository.SubjectRepository;
import com.saas.edu.dto.SchoolDto;
import com.saas.edu.service.AuditEntityService;
import com.saas.edu.service.SchoolService;

@Service
public class SchoolServiceImpl implements SchoolService{
	
	@Autowired
	private SchoolRepository schoolRepository;
	@Autowired
	private AuditEntityService auditEntityService;	
	@Autowired
	private SchoolSubjectMapRepository schoolSubjectMapRepository;	
	@Autowired
	private SchoolContactDetailsRepository schoolContactDetailsRepository;	
	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	@Transactional
	public void addSchool(SchoolDto schoolDto) {
		Schl school =  getSchool(schoolDto);
		school.setSchlCdeNme(getSchoolCode(schoolDto.getPin()));
		auditEntityService.updateAudit(school, null);
		school = schoolRepository.saveAndFlush(school);
		SchlCtctDet schlCtctDet = getSchlCtctDet(schoolDto);
		schlCtctDet.setSchl(school);
		schoolContactDetailsRepository.saveAndFlush(schlCtctDet);
		createSubjectMap(school);
	}

	private void createSubjectMap(Schl school) {
		List<Sbjct> subjects = subjectRepository.findAll();
		subjects.stream().forEach(subject -> {
			SchlSbjctMap schlSbjctMap = new SchlSbjctMap();
			auditEntityService.updateAudit(schlSbjctMap, null);
			schlSbjctMap.setSbjct(subject);
			schlSbjctMap.setSchl(school);
			schoolSubjectMapRepository.saveAndFlush(schlSbjctMap);
		});
		
	}

	private String getSchoolCode(String pin) {
		int schoolCount = getSchooCountByPin(pin);
		String schoolCode = "G" + (++schoolCount) + pin;
		return schoolCode;
	}

	private SchlCtctDet getSchlCtctDet(SchoolDto schoolDto) {
		SchlCtctDet schlCtctDet = new SchlCtctDet();
		schlCtctDet.setAddr(schoolDto.getAddress());
		schlCtctDet.setAddrType(schoolDto.getAddrType());
		schlCtctDet.setCity(schoolDto.getCity());
		schlCtctDet.setPin(schoolDto.getPin());
		updateAudit(schlCtctDet,schoolDto);
		return schlCtctDet;
	}

	private void updateAudit(AbstractAuditEntity entity, SchoolDto schoolDto) {
		entity.setCrtdDttm(new Date());
		entity.setLstUptdDttm(new Date());
		entity.setCrtdUsr("Lal");
		entity.setLstUptdUsr("Lal");
	}

	private Schl getSchool(SchoolDto schoolDto) {
		Schl school = new Schl();
		school.setCrtdDttm(schoolDto.getCreatedDate());
		school.setCrtdUsr(schoolDto.getCreatedUser());
		school.setLstUptdDttm(schoolDto.getLastUpdatedDate());
		school.setLstUptdUsr(schoolDto.getLastUpdatedUser());
		school.setSchlCdeNme(schoolDto.getSchoolCode());
		return school;
	}
	
	private int getSchooCountByPin(String pin){
		return schoolContactDetailsRepository.findByPin(pin).size();
	}

	@Override
	public List<SchoolDto> findByPinCode(String pin) {
		List<SchlCtctDet> schlCtctDets= schoolContactDetailsRepository.findByPin(pin);
		List<SchoolDto> schoolDtoList = new ArrayList<>();
		schlCtctDets.stream().forEach(schlCtctDet ->{
			schoolDtoList.add(getSchoolDto(schlCtctDet));
		});
		
		return schoolDtoList;
	}

	private SchoolDto getSchoolDto(SchlCtctDet schlCtctDet) {
		SchoolDto schoolDto = new SchoolDto();
		schoolDto.setAddress(schlCtctDet.getAddr());
		schoolDto.setAddrType(schlCtctDet.getAddrType());
		//TODO Archana - Fill the rest of values
		return schoolDto;
	}
	
	

}
