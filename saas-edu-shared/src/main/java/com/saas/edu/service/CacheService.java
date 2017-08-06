package com.saas.edu.service;

import org.springframework.stereotype.Service;

@Service
public class CacheService {

	public String getLookUpItem(String key, String defaultValue) {
		return defaultValue;
	}
}
