package com.rain.urs.service.mapper;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomJacksonObjectMapper extends ObjectMapper {
	private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
	
	public CustomJacksonObjectMapper() {
         this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
         this.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);
         this.setSerializationInclusion(Include.NON_EMPTY);
         this.setDateFormat(new SimpleDateFormat(DATE_FORMAT));
	}
}

