package com.cewb.app.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.data.domain.Page;

import com.cewb.app.dto.request.HrRequestDto;
import com.cewb.app.model.HRRequest;

import net.sf.jasperreports.engine.JRException;

public interface HRRequestService {
	
	Page<HRRequest> findAll(int pageNum);
	
	List<HRRequest> findByFilter(HrRequestDto hrRequest);
	
	HRRequest findById(Long id);
	
	HRRequest save(HRRequest request);
	
	HRRequest update(HRRequest request);
	
	HRRequest delete(Long id);
	
	byte[] generateReport(HrRequestDto request)  throws JRException, URISyntaxException, IOException;
	
}
