package com.saas.edu.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.saas.edu.dao.model.ClstrDat;


public interface ClusterServerDataRpository extends JpaRepository<ClstrDat, Long>  {
	
	@Query("select s from ClstrDat s where s.appNme=?1")
	public List<ClstrDat> findByAppNme(String appName);

}
