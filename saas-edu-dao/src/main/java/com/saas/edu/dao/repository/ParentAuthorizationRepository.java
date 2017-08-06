package com.saas.edu.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saas.edu.dao.model.PrntAuth;

public interface ParentAuthorizationRepository extends JpaRepository<PrntAuth, Long> {

}
