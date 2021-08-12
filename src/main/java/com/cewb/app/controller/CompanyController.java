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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cewb.app.model.Company;
import com.cewb.app.service.CompanyService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin
@Log4j2
public class CompanyController {
	
	@Autowired
	CompanyService companyService;
	
	//Create company
	@PostMapping("/companies")
	public Company createCompany(@RequestBody Company company) {
		log.info("Create company endpoint");
		return companyService.save(company);
	}
	
	//Read company
	@GetMapping("/companies")
	public Page<Company> readCompanies(@RequestParam(value = "page", defaultValue = "0") int pageNum) {
		log.info("Read companies page - " + pageNum);
		return companyService.findAll(pageNum);
	}
	
	//Read company
	@GetMapping("/companies/{id}")
	public Company readCompany(@PathVariable Long id) {
		log.info("Read company with id " + id);
		return companyService.findById(id);
	}
	
	//Update Company
	@PutMapping("/companies")
	public Company updateCompany(@RequestBody Company company) {
		log.info("Update company with id " + company.getId());
		companyService.update(company);
		return company;
	}
	
	//Delete Company
	@DeleteMapping("/companies/{id}")
	public Company deleteCompany(@PathVariable Long id) {
		log.info("Delete company with id " + id);
		return companyService.delete(id);
	}
}
