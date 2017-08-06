package com.saas.edu.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.edu.dao.model.Dvsn;
import com.saas.edu.dao.model.DvsnSchlGrdLvlMap;
import com.saas.edu.dao.model.GrdLvl;
import com.saas.edu.dao.model.Schl;
import com.saas.edu.dao.model.SchlGrdLvlMap;
import com.saas.edu.dao.repository.DivisionRepository;
import com.saas.edu.dao.repository.DivisionSchoolGradeLevelMapRepository;
import com.saas.edu.dao.repository.GradeLevelRepository;
import com.saas.edu.dao.repository.SchoolGradeLevelMapRepository;
import com.saas.edu.dao.repository.SchoolRepository;
import com.saas.edu.service.AuditEntityService;
import com.saas.edu.service.SchoolAdminService;

@Service
public class SchoolAdminServiceImpl implements SchoolAdminService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SchoolAdminServiceImpl.class);
	
	@Autowired 
	private GradeLevelRepository gradeLevelRepository;
	@Autowired 
	private DivisionRepository divisionRepository;
	@Autowired 
	private SchoolRepository schoolRepository;
	@Autowired 
	private SchoolGradeLevelMapRepository schoolGradeLevelMapRepository;
	@Autowired 
	private AuditEntityService auditEntityService;
	@Autowired 
	private DivisionSchoolGradeLevelMapRepository divisionSchoolGradeLevelMapRepository;
	
	
	@Override
	public void createGradeDivisionMap(Map<String, String[]> gradeDivMap, String schoolCode) {
		
		
		Schl schl =  schoolRepository.findBySchlCdeNme(schoolCode).get(0);
		
		gradeDivMap.forEach((key, value) ->{
			LOGGER.debug("Adding Grade {} and Division {}", key, value);
			
			GrdLvl grdLvl = gradeLevelRepository.findByGrdNme(key).get(0);
			
			SchlGrdLvlMap schlGrdLvlMap = new SchlGrdLvlMap();
			schlGrdLvlMap.setGrdLvl(grdLvl);
			schlGrdLvlMap.setSchl(schl);
			auditEntityService.updateAudit(schlGrdLvlMap, null);
			schlGrdLvlMap = schoolGradeLevelMapRepository.saveAndFlush(schlGrdLvlMap);
			
			for(int i=0; i<value.length; i++){
				LOGGER.debug("Mapping Grade {} and Division {}", key, value[i]);
				Dvsn dvsn = divisionRepository.findByDvsnNme(value[i]).get(0);
				DvsnSchlGrdLvlMap dvsnSchlGrdLvlMap = new DvsnSchlGrdLvlMap();
				dvsnSchlGrdLvlMap.setDvsn(dvsn);
				dvsnSchlGrdLvlMap.setSchlGrdLvlMap(schlGrdLvlMap);
				auditEntityService.updateAudit(dvsnSchlGrdLvlMap, null);
				divisionSchoolGradeLevelMapRepository.save(dvsnSchlGrdLvlMap);
				
			}
			
		});

	}

}
