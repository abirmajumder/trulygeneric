package com.trulygeneric.batch.service.impl;

import java.util.stream.Collectors;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trulygeneric.batch.core.factory.behaviour.IFlowFactory;
import com.trulygeneric.batch.core.pojo.JobResult;
import com.trulygeneric.batch.core.pojo.StepResult;
import com.trulygeneric.batch.service.behaviour.IBatchService;

@Service
@EnableBatchProcessing
public class BatchService implements IBatchService {

	@Autowired private IFlowFactory flowFactory;
    @Autowired private JobBuilderFactory jobBuilderFactory;
    @Autowired private JobRepository jobRepo;
	
	@Override
	public void start(String jobName) throws Exception {
		jobLauncher.run(
				jobBuilderFactory.get(jobName)
					.incrementer(new RunIdIncrementer())
					.start(flowFactory.create(jobName))
					.build().build(), createParam(jobName));
	}
	
	@Override
	public void validateJob( String jobName ) throws Exception {
		
		//String result = "{ \"stepResults\" : [ {\"stepName\":\"SP\", \"writeCount\":\"122\"} , {\"stepName\":\"Query\", \"writeCount\":\"21\" } ] }";
		//ObjectMapper om = new ObjectMapper();
		
		//JobResult rs = om.readValue(result, JobResult.class);
		
		//@SuppressWarnings("unchecked")
		//Map<String,Object> rs = om.readValue(result, Map.class);
		
		//System.out.println( "printing\n" + rs + "printing done\n");
		
		//System.out.println(jobExec);
		//System.out.println(qStep);
		//System.out.println(spStep);
		
		JobResult jResult 
			= new JobResult(jobName, "COMPLETED", "COMPLETED")
					.addStepResult( new StepResult("QUERY", "COMPLETED", "COMPLETED").setCount(0, 0, 1).setSkipCount(0, 0, 0) )
					.addStepResult( new StepResult("SP", "COMPLETED", "COMPLETED").setCount(0, 0, 1).setSkipCount(0, 0, 0) );

		JobExecution jobExec = jobRepo.getLastJobExecution(jResult.getJobName(), createParam(jobName));
		jResult.getStepResults().stream()
			   .map( sr -> jobRepo.getLastStepExecution(jobExec.getJobInstance(), sr.getStepName()))
			   .collect(Collectors.toMap( rr -> rr.getStepName(), rr -> rr))
			   ;
		
		StepExecution qStep = jobRepo.getLastStepExecution(jobExec.getJobInstance(), "QUERY");
		StepExecution spStep = jobRepo.getLastStepExecution(jobExec.getJobInstance(), "SP");
	}
	
	private JobParameters createParam( String jobName ) {
		return new JobParametersBuilder()
					.addString("JobID", jobName ) // + SysCommon.USR + String.valueOf(System.currentTimeMillis())
					.toJobParameters();
	}
	
	@Autowired private JobLauncher jobLauncher;

}
