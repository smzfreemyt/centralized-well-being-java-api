package com.cewb.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cewb.app.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
	
	
	
}
