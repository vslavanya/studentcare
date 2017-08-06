package com.saas.edu.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saas.edu.dao.model.Tchr;;

public interface TeacherRepository extends JpaRepository<Tchr, Long> {

}
