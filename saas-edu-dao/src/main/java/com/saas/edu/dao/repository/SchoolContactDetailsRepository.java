package com.saas.edu.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saas.edu.dao.model.SchlCtctDet;

public interface SchoolContactDetailsRepository extends JpaRepository<SchlCtctDet, Long>{
	public List<SchlCtctDet> findByPin(String pin);
}
