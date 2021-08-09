package com.cewb.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cewb.app.model.Service;

public interface ServiceRepository extends JpaRepository<Service, Long> {
	
	@Query("from Service s where s.company.id = ?1")
	public Page<Service> findByCompanyId(Long id, Pageable pageable);
	
}
