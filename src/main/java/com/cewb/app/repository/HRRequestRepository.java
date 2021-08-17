package com.cewb.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cewb.app.model.HRRequest;

public interface HRRequestRepository extends JpaRepository<HRRequest, Long> {
	
	@Query("select h from HRRequest h where h.createdAt >= :start and h.createdAt <= :end and h.department like :dprtmnt and "
			 + "h.classification like :class and h.status like :status")
	List<HRRequest> findByFilter(@Param(value = "start") Date startDate,@Param(value = "end") Date endDate,@Param(value = "dprtmnt") String department,
			@Param(value = "class") String classification, @Param(value = "status")String status);
	
}
