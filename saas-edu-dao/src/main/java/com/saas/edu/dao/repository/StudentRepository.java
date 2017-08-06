package com.saas.edu.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saas.edu.dao.model.Stdnt;;

public interface StudentRepository extends JpaRepository<Stdnt, Long> {

}
