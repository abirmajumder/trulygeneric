package com.trulygeneric.batch.service.behaviour;

public interface IBatchService {
	void start( String jobName ) throws Exception;
	void validateJob(String jobName) throws Exception;
}
