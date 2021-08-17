package com.cewb.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cewb.app.model.HRRequest;

public interface HRRequestRepository extends JpaRepository<HRRequest, Long> {
	
	@Query("from HRRequest h")
	List<HRRequest> findByFilter();
	
}
