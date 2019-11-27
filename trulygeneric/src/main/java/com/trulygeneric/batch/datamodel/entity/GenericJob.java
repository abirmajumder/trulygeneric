package com.trulygeneric.batch.datamodel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.trulygeneric.common.constants.SysCommon;

@Entity
@Table ( name = "cnf_job" )
public class GenericJob {
	@Id @GeneratedValue ( strategy = GenerationType.IDENTITY ) private Integer id;
	@Column ( name = "job_name", length = 100 ) private String jobName;
	@Column ( name = "application_id" ) private Integer applicationId;
	@Column ( name = "active", length = 1 ) private String active;
	
	public GenericJob() {
		setActive(SysCommon.Y);
	}
	
	public void setJobName ( String jobName ) { 
		this.jobName = jobName;
	}
	
	public Integer getApplicationId (  ) { 
		return this.applicationId;
	}
	
	public void setActive ( String active ) { 
		this.active = active;
	}
	
	public String getJobName (  ) { 
		return this.jobName;
	}
	
	public void setApplicationId ( Integer applicationId ) { 
		this.applicationId = applicationId;
	}
	
	public void setId ( Integer id ) { 
		this.id = id;
	}
	
	public Integer getId (  ) { 
		return this.id;
	}
	
	public String getActive (  ) { 
		return this.active;
	}

}