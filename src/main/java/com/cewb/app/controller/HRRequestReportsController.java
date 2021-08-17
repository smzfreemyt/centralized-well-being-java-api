package com.cewb.app.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cewb.app.config.RequestStatus;
import com.cewb.app.dto.request.HrRequestDto;
import com.cewb.app.model.HRRequest;
import com.cewb.app.service.HRRequestService;

import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@RestController
@RequestMapping("/api")
@Log4j2
public class HRRequestReportsController {
	
	@Autowired
	HRRequestService requestService;
	
	// Save reports by users
	@PostMapping("/reports")
	public HRRequest createRequest(@RequestBody HRRequest request) {
		log.info("Create report endpoint");
		request.setStatus(RequestStatus.PENDING.getDescription());
		
		return requestService.save(request);
	}
	
	// Get all reports
	@GetMapping("/reports")
	public Page<HRRequest> getReports(@RequestParam(value = "page", defaultValue = "0") int pageNum) {
		log.info("Get all reports");
		
		return requestService.findAll(pageNum);
	}
	
	// Update report status
	@PutMapping("/reports/status")
	public HRRequest updateRequest(@RequestBody HRRequest request) {
		log.info("Update report status endpoint");
		
		HRRequest item = requestService.findById(request.getId());
		item.setStatus(request.getStatus());
		
		return requestService.update(item);
	}
	
	@PostMapping("/reports/generate")
	public ResponseEntity<byte[]> generateReport(@RequestBody HrRequestDto hrRequest) throws JRException, IOException, URISyntaxException {
		try (InputStream in = getClass().getResourceAsStream("/reports/HRRequestReport.jrxml")) {
		    JasperReport jasperReport = JasperCompileManager.compileReport(in);
		    Map<String,Object> params = new HashMap<>();
		    
		    
		    JRBeanCollectionDataSource dataSource  = new JRBeanCollectionDataSource(requestService.findByFilter(hrRequest), false);
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, initializeParams(params, hrRequest), dataSource);
            
		    //For debugging purpose only
		    String path = "E://demoReportOutput.pdf";
            JasperExportManager.exportReportToPdfFile(jasperPrint,path);
		    
		    byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

            return new ResponseEntity<byte[]>(pdfBytes, HttpStatus.OK);
		}
	}
	
	private Map<String, Object> initializeParams(Map<String,Object> params, HrRequestDto hrRequest) throws URISyntaxException {
		File file = Paths.get(getClass().getClassLoader().getResource("reports/logo.png").toURI()).toFile();
		if(file.exists()) {
			params.put("companyLogo", file.getAbsolutePath());			
		}
		return params;
	}
	
	
}
