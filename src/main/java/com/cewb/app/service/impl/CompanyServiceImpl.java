package com.cewb.app.service.impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cewb.app.config.ConfigRepository;
import com.cewb.app.model.Company;
import com.cewb.app.repository.CompanyRepository;
import com.cewb.app.service.CompanyService;
import com.cewb.app.service.UserService;
import com.cewb.app.utility.AppUtility;

@Service
public class CompanyServiceImpl implements CompanyService {
	
	@Autowired
	CompanyRepository companyRepository;
	
	@Autowired
	UserService userService;
	
	@Override
	public Page<Company> findAll(int pageNum) {
		return companyRepository.findAll(PageRequest.of(pageNum, ConfigRepository.PER_PAGE));
	}

	@Override
	public Company findById(Long id) {
		return companyRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cant find company with id - " + id));
	}
	
	@Override
	public Page<Company> findByKeyword(int pageNum, String keyword) {
		return companyRepository.findByNameLike(AppUtility.getSqlKeyword(keyword), PageRequest.of(pageNum, 10));
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
