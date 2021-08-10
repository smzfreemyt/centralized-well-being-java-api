package com.cewb.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cewb.app.model.HRRequest;

public interface HRRequestRepository extends JpaRepository<HRRequest, Long> {

}
