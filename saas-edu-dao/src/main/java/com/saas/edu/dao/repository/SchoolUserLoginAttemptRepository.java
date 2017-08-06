package com.saas.edu.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saas.edu.dao.model.SchlUsrLognAtmt;;

public interface SchoolUserLoginAttemptRepository extends JpaRepository<SchlUsrLognAtmt, Long> {

}
