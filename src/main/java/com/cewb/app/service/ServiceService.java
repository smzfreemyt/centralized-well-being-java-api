package com.cewb.app.service;

import org.springframework.data.domain.Page;

import com.cewb.app.model.Service;

public interface ServiceService {
	
	Page<Service> findAll(Long companyId, int pageNum);
	
	Page<Service> findByKeyword(Long companyId, int pageNum, String keyword);
	
	Service findById(Long companyId, Long serviceId);
	
	Service save(Long companyId, Service company);
	
	Service update(Long companyId, Service company);
	
	Service delete(Long companyId, Long serviceId);
	
}
