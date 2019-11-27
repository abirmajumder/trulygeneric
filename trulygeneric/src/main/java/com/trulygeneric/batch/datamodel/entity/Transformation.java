package com.trulygeneric.batch.datamodel.entity;

import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.trulygeneric.common.constants.SysCommon;

@Entity
@Table ( name = "cnf_transformation" )
public class Transformation {

	@Id @GeneratedValue ( strategy = GenerationType.IDENTITY ) private Integer id;
	@Column ( name = "target_field", length = 100 ) private String targetField;
	@Column ( name = "target_type", length = 500 ) private String targetType;
	@Column ( name = "target_pattern", length = 100 ) private String targetPattern;
	@Column ( name = "transformation_type", length = 50 ) private String transformationType;
	@Column ( name = "transformation_params", length = 500 ) private String transformationParams;
	@Column ( name = "step_sequence_id" ) private Integer stepSequenceId;
	@Column ( name = "field_id" ) private Integer fieldId;
	@Column ( name = "active", length = 1 ) private String active;
	
	public Transformation() {
		setActive(SysCommon.Y);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTargetField() {
		return targetField;
	}

	public void setTargetField(String targetField) {
		this.targetField = targetField;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getTargetPattern() {
		return targetPattern;
	}

	public void setTargetPattern(String targetPattern) {
		this.targetPattern = targetPattern;
	}

	public String getTransformationType() {
		return transformationType;
	}

	public void setTransformationType(String transformationType) {
		this.transformationType = transformationType;
	}

	public String getTransformationParams() {
		return transformationParams;
	}

	public void setTransformationParams(String transformationParams) {
		this.transformationParams = transformationParams;
	}

	public Integer getStepSequenceId() {
		return stepSequenceId;
	}

	public void setStepSequenceId(Integer stepSequenceId) {
		this.stepSequenceId = stepSequenceId;
	}

	public Integer getFieldId() {
		return fieldId;
	}

	public void setFieldId(Integer fieldId) {
		this.fieldId = fieldId;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}
	
}