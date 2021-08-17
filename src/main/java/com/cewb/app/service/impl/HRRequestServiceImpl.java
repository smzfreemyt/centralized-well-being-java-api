package com.cewb.app.service.impl;

import com.cewb.app.config.ConfigRepository;
import com.cewb.app.dto.request.HrRequestDto;
import com.cewb.app.model.HRRequest;
import com.cewb.app.repository.HRRequestRepository;
import com.cewb.app.service.HRRequestService;
import com.cewb.app.utility.AppUtility;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class HRRequestServiceImpl implements HRRequestService {
	
	@Autowired
	HRRequestRepository requestRepository;

	@Override
	public Page<HRRequest> findAll(int pageNum) {
		return requestRepository.findAll(PageRequest.of(pageNum, ConfigRepository.PER_PAGE));
	}

	@Override
	public List<HRRequest> findByFilter(HrRequestDto hrRequest) {
		return requestRepository.findByFilter(
				hrRequest.getStartDate(),
				hrRequest.getEndDate(),
				AppUtility.getSqlKeyword(hrRequest.getDepartment()),
				AppUtility.getSqlKeyword(hrRequest.getClassification()),
				AppUtility.getSqlKeyword(hrRequest.getStatus())
			);
	}
	
	@Override
	public HRRequest findById(Long id) {
		HRRequest result = requestRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cant find HR request with id - " + id));
		
        return result;
	}
	
	@Override
	public HRRequest save(HRRequest request) {
		return requestRepository.save(request);
	}

	@Override
	public HRRequest update(HRRequest request) {
		findById(request.getId());
		return save(request);
	}


	@Override
	public HRRequest delete(Long id) {
		HRRequest request = findById(id);
		
		requestRepository.delete(request);
		return request;
	}

	@Override
	public byte[] generateReport(HrRequestDto request) throws JRException, URISyntaxException, IOException {
		try (InputStream in = getClass().getResourceAsStream("/reports/HRRequestReport.jrxml")) {
		    JasperReport jasperReport = JasperCompileManager.compileReport(in);
		    Map<String,Object> params = new HashMap<>();
		    
		    JRBeanCollectionDataSource dataSource  = new JRBeanCollectionDataSource(findByFilter(request), false);
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, initializeParams(params, request), dataSource);
            
		    byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);
		    byte[] pdfBase64 = Base64.getEncoder().encode(pdfBytes);
            return pdfBase64;
		}
	}
	
	private Map<String, Object> initializeParams(Map<String,Object> params, HrRequestDto hrRequest) throws URISyntaxException {
		File file = Paths.get(getClass().getClassLoader().getResource("reports/logo.png").toURI()).toFile();
		if(file.exists()) {
			params.put("companyLogo", file.getAbsolutePath());			
		} 
		params.put("dateFrom", hrRequest.getStartDate());
		params.put("dateTo", hrRequest.getEndDate());
		params.put("classification", hrRequest.getClassification());
		params.put("department", hrRequest.getDepartment());
		params.put("status", hrRequest.getStatus());
		params.put("type", hrRequest.getType());
		return params;
	}

}
