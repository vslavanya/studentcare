package com.saas.edu.dao.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saas.edu.dao.model.Dvsn;

public interface DivisionRepository extends JpaRepository<Dvsn, Long>{

	public List<Dvsn>  findByDvsnNme(String dvsnNme);
}
