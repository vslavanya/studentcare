package com.saas.edu.web;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.saas.edu.constant.SaasConstants;
import com.saas.edu.dto.StatusDto;
import com.saas.edu.excel.ExcelParsingService;
import com.saas.edu.service.ResponseStatusService;

@RestController
@RequestMapping("school/admin")
public class ExcelTemplateController {
	
	@Autowired
	private ExcelParsingService excelParsingService;
	@Autowired
	private ResponseStatusService responseStatusService;
	private static final Logger LOGGER = LoggerFactory.getLogger(ExcelTemplateController.class);
	
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public @ResponseBody
	StatusDto uploadFileHandler(@RequestParam("schoolCode") String schoolCode,
			@RequestParam("file") MultipartFile file) {
		
		String tranId = UUID.randomUUID().toString();
		MDC.put(SaasConstants.TRAN_ID, tranId);
		long startTime = System.currentTimeMillis();
		LOGGER.debug("Starting Module "+this.getClass().getSimpleName());
		
		if(!file.isEmpty()){
			try {
				excelParsingService.parseExcel(file.getInputStream(), "GRADE-DIVISION", schoolCode);				
			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error(" Error while processing ExcelTemplateController. uploadFileHandler(), {}", e.getMessage());
				return responseStatusService.getStatusDto(e);
			}
		}
		long endTime = System.currentTimeMillis();
		LOGGER.debug("Finished Module {}, time Taken = {}",this.getClass().getSimpleName(), endTime - startTime );
		return responseStatusService.getStatusDto();

	}

	

}
