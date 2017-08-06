package com.saas.edu.web;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.saas.edu.constant.SaasConstants;
import com.saas.edu.dto.ExamGroupDto;
import com.saas.edu.dto.StatusDto;
import com.saas.edu.service.ExamService;
import com.saas.edu.service.ResponseStatusService;

@RestController
@RequestMapping("admin/school")
public class ExamController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExamController.class);
	@Autowired
	private ExamService examService;
	@Autowired
	private ResponseStatusService responseStatusService;
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST , value = "addexam" , produces="application/json")
	public StatusDto addExamGroup(@RequestBody ExamGroupDto  examGroupDto){
		String tranId = UUID.randomUUID().toString();
		MDC.put(SaasConstants.TRAN_ID, tranId);
		long startTime = System.currentTimeMillis();
		LOGGER.debug("Starting Module "+this.getClass().getSimpleName());
		try{
			examService.createExam(examGroupDto);
		}catch(Exception e){
			LOGGER.error("Error while addExamGroup ",e);
			return responseStatusService.getStatusDto(e);
		}
		long endTime = System.currentTimeMillis();
		LOGGER.debug("Finished Module {}, time Taken = {}",this.getClass().getSimpleName(), endTime - startTime );
		return responseStatusService.getStatusDto();
	}

}
