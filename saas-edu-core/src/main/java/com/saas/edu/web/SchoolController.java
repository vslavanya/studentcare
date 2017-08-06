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
import com.saas.edu.dto.SchoolDto;
import com.saas.edu.dto.StatusDto;
import com.saas.edu.service.ResponseStatusService;
import com.saas.edu.service.SchoolService;

@RestController
@RequestMapping("admin/school")
public class SchoolController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ExamController.class);
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private ResponseStatusService responseStatusService;
	
	@ResponseBody
	@RequestMapping(method=RequestMethod.POST , value = "add" , produces="application/json")
	public StatusDto addSchool(@RequestBody SchoolDto  schoolDto){
		String tranId = UUID.randomUUID().toString();
		MDC.put(SaasConstants.TRAN_ID, tranId);
		long startTime = System.currentTimeMillis();
		LOGGER.debug("Starting Module "+this.getClass().getSimpleName());
		try{
			schoolService.addSchool(schoolDto);
		}catch(Exception e){
			LOGGER.error("Error while adding school",e);
			return responseStatusService.getStatusDto(e);
		}
		long endTime = System.currentTimeMillis();
		LOGGER.debug("Finished Module {}, time Taken = {}",this.getClass().getSimpleName(), endTime - startTime );
		return responseStatusService.getStatusDto();
		
	}

}
