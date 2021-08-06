package com.cewb.app.service.impl;

import java.util.Optional;

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
		Optional<Company> result = companyRepository.findById(id);

        Company company;

        if(result.isPresent())
            company = result.get();
        else
            throw new RuntimeException("Cant find ompany with id - " + id);
        return company;
	}

	@Override
	public Company save(Company company) {
        return companyRepository.save(company);
	}

	@Override
	public void delete(Long id) {
		findById(id);
		companyRepository.deleteById(id);
	}
	
}
