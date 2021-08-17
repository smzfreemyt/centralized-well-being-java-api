package com.cewb.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cewb.app.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{
	
	public Page<Company> findByNameLike(String keyword, Pageable pageable);
	
}
