package com.trulygeneric.batch.core.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JobResult {
	
	private String jobName;
	private String status;
	private String exitStatus;
	
	private List<StepResult> stepResults;
	private Map<String,StepResult> stepResultMap;

	public JobResult(String jobName, String status, String exitStatus) {
		this.jobName = jobName;
		this.status = status;
		this.exitStatus = exitStatus;
		this.stepResults = new ArrayList<>();
		this.stepResultMap = new HashMap<>();
	}
	
	public JobResult addStepResult( StepResult result ) {
		this.stepResults.add(result);
		this.stepResultMap.put(result.getStepName(), result);
		return this;
	}

	public List<StepResult> getStepResults() {
		return stepResults;
	}

	public void setStepResults(List<StepResult> stepResults) {
		this.stepResults = stepResults;
	}
	
	public String getJobName() {
		return jobName;
	}

	public String getStatus() {
		return status;
	}

	public String getExitStatus() {
		return exitStatus;
	}

	@Override
	public String toString() {
		return "JobResult [stepResults=" + stepResults.stream().map( Object :: toString ).collect(Collectors.joining(" | ")) + "]";
	}

}
