package com.cewb.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cewb.app.model.Service;
import com.cewb.app.service.ServiceService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/companies/{companyId}")
@CrossOrigin
@Log4j2
public class ServiceController {
	
	@Autowired
	ServiceService serviceService;
	
	//Create service
	@PostMapping("/services")
	public Service createService(@RequestBody Service service, @PathVariable(name = "companyId") Long companyId) {
		log.info("Create service endpoint");
		return serviceService.save(companyId, service);
	}
	
	//Read services
	@GetMapping("/services")
	public Page<Service> readServices(@RequestParam(value = "page", defaultValue = "0") int pageNum, @PathVariable(name = "companyId") Long companyId) {
		log.info("Read services page - " + pageNum);
		return serviceService.findAll(companyId, pageNum);
	}
	
	//Read services
	@GetMapping("/services/search")
	public Page<Service> searchServices(@RequestParam(value = "page", defaultValue = "0") int pageNum, @PathVariable(name = "companyId") Long companyId, @RequestParam(defaultValue = "", value = "search") String search) {
		log.info("Read services page - " + pageNum);
		return serviceService.findByKeyword(companyId, pageNum, search);
	}
	
	//Read service
	@GetMapping("/services/{serviceId}")
	public Service readService( @PathVariable(name = "companyId") Long companyId,  @PathVariable(name = "serviceId") Long serviceId) {
		log.info("Read service with id " + serviceId);
		return serviceService.findById(companyId, serviceId);
	}
	
	//Update service
	@PutMapping("/services")
	public Service updateService(@RequestBody Service service, @PathVariable(name = "companyId") Long companyId) {
		log.info("Update service with id " + service.getId());
		serviceService.update(companyId, service);
		return service;
	}
	
	//Delete Service
	@DeleteMapping("/services/{serviceId}")
	public Service deleteService(@PathVariable(name = "companyId") Long companyId,  @PathVariable(name = "serviceId") Long serviceId) {
		log.info("Delete service with id " + serviceId);
		return serviceService.delete(companyId, serviceId);
	}
}
