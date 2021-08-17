package com.cewb.app.dto.request;

import java.util.Date;

import lombok.Data;

@Data
public class HrRequestDto {
	
	private Date startDate = new Date(Long.MIN_VALUE);

	private Date endDate = new Date();
	
	private String department = "";
	
	private String classification = "";
	
	private String type = "";
	
	private String status = "";
	
}
