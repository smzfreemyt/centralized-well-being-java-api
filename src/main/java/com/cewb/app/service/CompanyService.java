package com.cewb.app.service;

import org.springframework.data.domain.Page;

import com.cewb.app.model.Company;

public interface CompanyService {
	
	Page<Company> findAll(int pageNum);
	
	Page<Company> findByKeyword(int pageNum, String keyword);
	
	Company findById(Long id);
	
	Company save(Company company);
	
	Company update(Company company);
	
	Company delete(Long id);
	
}
