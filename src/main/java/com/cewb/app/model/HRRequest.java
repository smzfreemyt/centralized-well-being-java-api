package com.cewb.app.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hr_request")
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class HRRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Email is required")
	private String email;
	
	@NotEmpty(message = "Requestor is required")
	private String requestor;
	
	@NotEmpty(message = "Department is required")
	private String department;	
		
	@NotEmpty(message = "Request classification is required")
	private String classification;
	
	@NotEmpty(message = "Request type is required")
	private String type;
	
	@NotEmpty(message = "Purpose of Request is required")
	private String purpose;
	
	@NotEmpty(message = "Request Details is required")
	private String details;
	
	@Column(name = "coa_emp_name")
	private String coaEmpName;
	
	@Column(name = "coa_current_approver")
	private String coaCurrentApprover;
	
	@Column(name = "coa_project_name")
	private String coaProjectName;
	
	@Column(name = "coa_new_approver_approver")
	private String coaNewApproverApprover;
		
	@Column(name = "status")
	private String status;
	
	@Column(name = "coa_effective_date")
	private Date coaEffectiveDate;
	
	@CreatedDate
	@Column(name = "created_at", updatable = false)
	private Date createdAt;
	
	@JsonIgnore
	@CreatedBy
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class, 
		cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "created_by")
	private User user;
	
}
