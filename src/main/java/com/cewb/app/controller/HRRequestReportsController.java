package com.cewb.app.controller;

import com.cewb.app.config.RequestStatus;
import com.cewb.app.model.HRRequest;
import com.cewb.app.service.HRRequestService;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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

	//Delete request
	@DeleteMapping("/reports/{id}")
	public HRRequest deleteCompany(@PathVariable Long id) {
		log.info("Delete request with id " + id);
		return requestService.delete(id);
	}
	
	@GetMapping("/reports/generate")
	public ResponseEntity<byte[]> generateReport() throws JRException, IOException {
		try (InputStream in = getClass().getResourceAsStream("/reports/HRRequestReport.jrxml")) {
		    JasperReport jasperReport = JasperCompileManager.compileReport(in);
		    Map<String,Object> params = new HashMap<>();
		    
		    JRBeanCollectionDataSource dataSource  = new JRBeanCollectionDataSource(requestService.findAll(0).getContent(), false);
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

            return new ResponseEntity<byte[]>(pdfBytes, HttpStatus.OK);
		}
	}
	
	
}
