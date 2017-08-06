package com.saas.edu.service.impl;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.edu.dao.model.Exm;
import com.saas.edu.dao.model.ExmGrp;
import com.saas.edu.dao.model.Schl;
import com.saas.edu.dao.model.SchlSbjctMap;
import com.saas.edu.dao.repository.ExamGroupRepository;
import com.saas.edu.dao.repository.ExamRepository;
import com.saas.edu.dao.repository.SchoolRepository;
import com.saas.edu.dto.ExamDto;
import com.saas.edu.dto.ExamGroupDto;
import com.saas.edu.service.AuditEntityService;
import com.saas.edu.service.ExamService;


@Service
public class ExamServiceImpl implements ExamService {

	
	@Autowired
	private ExamGroupRepository examGroupRepository;
	@Autowired
	private ExamRepository examRepository;
	@Autowired
	private SchoolRepository schoolRepository;
	@Autowired
	private AuditEntityService auditEntityService;


	@Override
	@Transactional
	public void createExam(ExamGroupDto examGroupDto) {
		
		ExmGrp exmGrp = buildExamGroup(examGroupDto);
		Schl schl = schoolRepository.findBySchlCdeNme(examGroupDto.getSchoolCode()).get(0);
		Set<SchlSbjctMap> schlSbjctMaps = schl.getSchlSbjctMaps();
		int i=1;
		for(SchlSbjctMap schlSbjctMap : schlSbjctMaps){
			Exm exm = new Exm();
			exm.setExmGrp(exmGrp);
			exm.setSchlSbjctMap(schlSbjctMap);
			exm.setExmCdeNme("E" + (i++) + examGroupDto.getExamGroupCode());
			auditEntityService.updateAudit(exm, null);
			examRepository.saveAndFlush(exm);
		}
	}


	
	private  ExmGrp buildExamGroup(ExamGroupDto examGroupDto) {
		ExmGrp  examGrp = new ExmGrp();
		examGrp.setGrpCdeNme(examGroupDto.getExamGroupCode());
		examGrp.setExmGrpDesc(examGroupDto.getDescription());
		examGrp.setYear(examGroupDto.getYear());
		examGrp.setTypeNme(examGroupDto.getExamType());
		List<Schl> schlList = schoolRepository.findBySchlCdeNme(examGroupDto.getSchoolCode());
		examGrp.setSchl(schlList.get(0));
		auditEntityService.updateAudit(examGrp, null);
		return examGroupRepository.saveAndFlush(examGrp);	
	}


}
