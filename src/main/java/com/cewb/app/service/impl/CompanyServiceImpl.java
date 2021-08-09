package com.cewb.app.service.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cewb.app.model.Company;
import com.cewb.app.repository.CompanyRepository;
import com.cewb.app.service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Override
	public Page<Company> findAll(int pageNum) {
		return companyRepository.findAll(PageRequest.of(pageNum, 2));
	}

	@Override
	public Company findById(Long id) {
		Company result = companyRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cant find company with id - " + id));
		
        return result;
	}

	@Override
	public Company save(Company company) {
        return companyRepository.save(company);
	}

	@Override
	public Company update(Company company) {
		findById(company.getId());
		return save(company);
	}

	@Override
	public Company delete(Long id) {
		Company company = findById(id);
		
		companyRepository.delete(company);
		return company;
	}
	
}
