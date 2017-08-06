package com.saas.edu.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saas.edu.dao.model.Atndnc;

public interface AttendanceRepository extends JpaRepository<Atndnc, Long>  {

}
