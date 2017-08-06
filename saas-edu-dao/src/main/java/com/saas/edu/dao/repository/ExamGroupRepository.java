package com.saas.edu.dao.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saas.edu.dao.model.ExmGrp;

public interface ExamGroupRepository extends JpaRepository<ExmGrp, Serializable>{
	public List<ExmGrp> findByGrpCdeNme(String cdeNme );

}
