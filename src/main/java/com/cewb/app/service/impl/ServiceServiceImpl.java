package com.cewb.app.service.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cewb.app.repository.ServiceRepository;
import com.cewb.app.service.CompanyService;
import com.cewb.app.service.ServiceService;
import com.cewb.app.utility.AppUtility;
import com.cewb.app.model.Company;
import com.cewb.app.model.Service;

@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {
	
	@Autowired
	ServiceRepository serviceRepository;
	@Autowired 
	CompanyService companyService;
	
	@Override
	public Page<Service> findAll(Long companyId, int pageNum) {
		companyService.findById(companyId);
		return serviceRepository.findByCompanyId(companyId, PageRequest.of(pageNum, 2));
	}
	
	@Override
	public Service findById(Long companyId, Long serviceId) {
		Company company = companyService.findById(companyId);
		Service service = company.getServices().stream()
				.filter(s -> s.getId() == serviceId)
				.findAny()
				.orElseThrow(() -> new EntityNotFoundException("Cant find service with id - " + serviceId));

		return service;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Service save(Long companyId, Service service) {
		if(!AppUtility.isNull(companyId)) {			
			Company company = companyService.findById(companyId);
			service.setCompany(company);
		}
		
		return serviceRepository.save(service);
	}
	
	@Override
	@Transactional
	public Service update(Long companyId, Service service) {
		Company company = companyService.findById(companyId);
		service.setCompany(company);
		
		findById(companyId, service.getId());
		return save(null, service);
	}

	@Override
	public Service delete(Long companyId, Long serviceId) {
		Service service = findById(companyId, serviceId);
		
		System.out.println(service.getName());
		serviceRepository.delete(service);
		return service;
	}

}
