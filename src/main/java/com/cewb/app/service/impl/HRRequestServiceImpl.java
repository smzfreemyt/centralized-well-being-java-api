package com.cewb.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cewb.app.model.HRRequest;
import com.cewb.app.repository.HRRequestRepository;
import com.cewb.app.service.HRRequestService;

@Service
public class HRRequestServiceImpl implements HRRequestService {
	
	@Autowired
	HRRequestRepository requestRepository;

	@Override
	public HRRequest save(HRRequest request) {
		return requestRepository.save(request);
	}
}
