package com.cewb.app.service.impl;

import com.cewb.app.config.ConfigRepository;
import com.cewb.app.model.HRRequest;
import com.cewb.app.repository.HRRequestRepository;
import com.cewb.app.service.HRRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class HRRequestServiceImpl implements HRRequestService {
	
	@Autowired
	HRRequestRepository requestRepository;

	@Override
	public Page<HRRequest> findAll(int pageNum) {
		return requestRepository.findAll(PageRequest.of(pageNum, ConfigRepository.PER_PAGE));
	}

	@Override
	public HRRequest findById(Long id) {
		HRRequest result = requestRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cant find HR request with id - " + id));
		
        return result;
	}
	
	@Override
	public HRRequest save(HRRequest request) {
		return requestRepository.save(request);
	}

	@Override
	public HRRequest update(HRRequest request) {
		findById(request.getId());
		return save(request);
	}


	@Override
	public HRRequest delete(Long id) {
		HRRequest request = findById(id);
		
		requestRepository.delete(request);
		return request;
	}
}
