package com.saas.edu.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saas.edu.dao.model.Ntfn;;

public interface NotificationRepository extends JpaRepository<Ntfn, Long>  {

}
