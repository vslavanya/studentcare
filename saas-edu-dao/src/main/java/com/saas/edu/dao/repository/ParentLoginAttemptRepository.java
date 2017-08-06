package com.saas.edu.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saas.edu.dao.model.PrntLognAtmt;

public interface ParentLoginAttemptRepository extends JpaRepository<PrntLognAtmt, Long>  {

}
