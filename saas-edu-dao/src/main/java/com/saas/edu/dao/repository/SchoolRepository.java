package com.saas.edu.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saas.edu.dao.model.Schl;

public interface SchoolRepository extends JpaRepository<Schl, Long> {
	public List<Schl>  findBySchlCdeNme(String schlCde);
	

}
