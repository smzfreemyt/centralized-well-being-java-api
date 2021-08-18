package com.cewb.app.repository;

import com.cewb.app.model.HRRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface HRRequestRepository extends JpaRepository<HRRequest, Long> {
	@Query("from HRRequest h where h.requestor like ?1")
	Page<HRRequest> findByRequestorLike(String sqlKeyword, PageRequest of);

	@Query("select h from HRRequest h where h.createdAt >= :start and h.createdAt <= :end and h.department like :dprtmnt and "
			 + "h.classification like :class and h.status like :status")
	List<HRRequest> findByFilter(@Param(value = "start") Date startDate,@Param(value = "end") Date endDate,@Param(value = "dprtmnt") String department,
			@Param(value = "class") String classification, @Param(value = "status")String status);
}
