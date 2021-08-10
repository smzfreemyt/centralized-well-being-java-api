package com.cewb.app.service;

import org.springframework.data.domain.Page;

import com.cewb.app.model.HRRequest;

public interface HRRequestService {
	
	Page<HRRequest> findAll(int pageNum);
	
	HRRequest findById(Long id);
	
	HRRequest save(HRRequest request);
	
	HRRequest update(HRRequest request);
	
	HRRequest delete(Long id);
	
}
