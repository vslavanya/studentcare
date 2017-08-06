package com.saas.edu.service;

import java.io.Serializable;

import com.saas.edu.dao.model.AbstractAuditEntity;

public interface AuditEntityService {

	void updateAudit(AbstractAuditEntity entity, Serializable serializable);

}
