package com.saas.edu.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saas.edu.dao.model.GrdLvl;

public interface GradeLevelRepository extends JpaRepository<GrdLvl, Long>{
	
	public List<GrdLvl> findByGrdNme(String grdNme);
	
}

