package com.saas.edu.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.saas.edu.dao.model.AbstractAuditEntity;
import com.saas.edu.service.AuditEntityService;

@Service
public class AuditEntityServiceImpl implements AuditEntityService {

	@Override
	public void updateAudit(AbstractAuditEntity entity, Serializable serializable) {
		entity.setCrtdDttm(new Date());
		entity.setLstUptdDttm(new Date());
		entity.setCrtdUsr("SAAS_SCHOOL_ADMIN");
		entity.setLstUptdUsr("SAAS_SCHOOL_ADMIN");
	}
}
