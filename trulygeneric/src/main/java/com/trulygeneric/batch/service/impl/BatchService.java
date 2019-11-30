package com.trulygeneric.batch.service.impl;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trulygeneric.batch.core.factory.behaviour.IFlowFactory;
import com.trulygeneric.batch.service.behaviour.IBatchService;
import com.trulygeneric.common.constants.SysCommon;

@Service
@EnableBatchProcessing
public class BatchService implements IBatchService {

	@Autowired private IFlowFactory flowFactory;
    @Autowired private JobBuilderFactory jobBuilderFactory;
	
	@Override
	public void start(String jobName) throws Exception {
		jobLauncher.run(
				jobBuilderFactory.get(jobName)
					.incrementer(new RunIdIncrementer())
					.start(flowFactory.create(jobName))
					.build().build(), createParam(jobName));
	}
	
	private JobParameters createParam( String jobName ) {
		return new JobParametersBuilder()
					.addString("JobID", jobName + SysCommon.USR + String.valueOf(System.currentTimeMillis()))
					.toJobParameters();
	}
	
	@Autowired private JobLauncher jobLauncher;

}
