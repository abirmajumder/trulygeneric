package com.trulygeneric.batch.datamodel.entity;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.trulygeneric.common.constants.SysCommon;

@Entity
@Table ( name = "cnf_field" )
public class DataField {
	@Id @GeneratedValue ( strategy = GenerationType.IDENTITY ) private Integer id;
	@Column ( name = "field_name", length = 100 ) private String fieldName;
	@Column ( name = "field_type", length = 500 ) private String fieldType;
	@Column ( name = "field_pattern", length = 100 ) private String fieldPattern;
	@Column ( name = "is_required", length = 1 ) private String isRequired;
	@Column ( name = "job_sequence_id" ) private Integer jobSequenceId;
	@Column ( name = "active", length = 1 ) private String active;
	
	public DataField() {
		setActive(SysCommon.Y);
	}
	
	public void setFieldName ( String fieldName ) { 
		this.fieldName = fieldName;
	}
	
	public void setIsRequired ( String isRequired ) { 
		this.isRequired = isRequired;
	}
	
	public void setFieldPattern ( String fieldPattern ) { 
		this.fieldPattern = fieldPattern;
	}
	
	public String getFieldType (  ) { 
		return this.fieldType;
	}
	
	public Integer getId (  ) { 
		return this.id;
	}
	
	public String getFieldPattern (  ) { 
		return this.fieldPattern;
	}
	
	public void setActive ( String active ) { 
		this.active = active;
	}
	
	public Integer getJobSequenceId (  ) { 
		return this.jobSequenceId;
	}
	
	public String getIsRequired (  ) { 
		return this.isRequired;
	}
	
	public void setFieldType ( String fieldType ) { 
		this.fieldType = fieldType;
	}
	
	public void setId ( Integer id ) { 
		this.id = id;
	}
	
	public void setJobSequenceId ( Integer jobSequenceId ) { 
		this.jobSequenceId = jobSequenceId;
	}
	
	public String getActive (  ) { 
		return this.active;
	}
	
	public String getFieldName (  ) { 
		return this.fieldName;
	}

}