package com.cewb.app.config;

import com.cewb.app.utility.AppUtility;

public enum RequestStatus {
	
	PENDING(1, "Pending"),
	DECLINED(2, "Declined"),
	INPROCESS(1, "In Process"),
	COMPLETED(1, "Completed");
	
	private RequestStatus(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	private Integer id;
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return !AppUtility.isNull(description) ? description : "";
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static RequestStatus getInstance(Integer id) {
		for (RequestStatus value : RequestStatus.values())
			if (value.getId().equals(id))
				return value;
		return null;
	}
}
