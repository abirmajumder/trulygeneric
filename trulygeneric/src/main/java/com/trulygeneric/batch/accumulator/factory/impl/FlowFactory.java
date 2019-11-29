package com.trulygeneric.batch.accumulator.factory.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trulygeneric.batch.accumulator.factory.behaviour.IFlowFactory;
import com.trulygeneric.batch.accumulator.factory.behaviour.IStepFactory;
import com.trulygeneric.batch.constants.JobAction;
import com.trulygeneric.batch.datamodel.entity.GenericJob;
import com.trulygeneric.batch.datamodel.entity.JobSequence;
import com.trulygeneric.batch.datamodel.entity.StepSequence;
import com.trulygeneric.batch.datamodel.repository.GenericJobRepository;
import com.trulygeneric.batch.datamodel.repository.JobSequenceRepository;
import com.trulygeneric.batch.datamodel.repository.StepSequenceRepository;
import com.trulygeneric.common.constants.SysCommon;
import com.trulygeneric.common.util.NumberUtil;
import com.trulygeneric.common.util.ParseUtil;

@Component
public class FlowFactory implements IFlowFactory {

	@Autowired private IStepFactory stepFactory;
	@Autowired private GenericJobRepository jobRepo;
	@Autowired private JobSequenceRepository jobSeqRepo;
	@Autowired private StepSequenceRepository stepSeqRepo;
	
	@Override
	public SimpleFlow create(String jobName) throws Exception {
		GenericJob job = jobRepo.findByJobNameAndActive(jobName, SysCommon.Y);
		List<JobSequence> sequences = jobSeqRepo.findByJobIdAndActiveOrderBySequenceAsc( job.getId(), SysCommon.Y);
		FlowBuilder<SimpleFlow> flowBuilder = new FlowBuilder<>( jobName.toUpperCase() + SysCommon.USR + SysCommon.FLW );
		boolean started = false;
		for( int i=0; i<sequences.size();) {
			int count = 1;
			while( ( i + count ) < sequences.size() 
					&& NumberUtil.equals( sequences.get(i).getSequence(), sequences.get(i+count).getSequence()) ) 
				count++;
			if( count > 1 )
				addToFlow( started, flowBuilder, createSplit(sequences, i, count));
			else 
				addToFlow(started, flowBuilder, sequences.get(i));
			 i += count;
			 started = true;
		}
		flowBuilder.end();
		return flowBuilder.build();
	}
	
	@Override
	public SimpleFlow createProcessFlow(JobSequence seq) throws Exception {
		Map<String,String> params = ParseUtil.parseParamStr(seq.getStepParams());
		final String processName = StringUtils.substringBefore(params.get("name"), SysCommon.DOT).toUpperCase() ;
		List<StepSequence> sequences = this.stepSeqRepo.findByjobSequenceIdAndActiveOrderBySequenceAsc(seq.getId(), SysCommon.Y);
		FlowBuilder<SimpleFlow> flowBuilder = new FlowBuilder<>( processName+ SysCommon.USR + SysCommon.FLW );
		boolean started = false,processStarted = false;
		for( int i =0; i<sequences.size(); i++ ) {
			String readerPart = null;
			int count = 1;
			while( ( i + count ) < sequences.size() && 
					NumberUtil.equals( Math.round(sequences.get(i).getSequence()), Math.round(sequences.get(i+count).getSequence())) ) { 
				count++;
				if( null ! )
			}
			if( count > 1 ) {
				
			} else {
				
			}
			
		}
		return null;
	}
	
	@Override
	public SimpleFlow createSplit(List<JobSequence> sequences, int index, int spread) {
		return null;
	}

	@Override
	public SimpleFlow createComposite(List<StepSequence> sequences, int index, int spread) {//For PROCESS
		return null;
	}

	private void addToFlow( boolean started, FlowBuilder<SimpleFlow> flowBuilder, JobSequence seq) throws Exception {
		if( JobAction.get(seq.getStepName()).isProcess() )
			addToFlow(started, flowBuilder, createProcessFlow(seq));
		else 
			addToFlow(started, flowBuilder, stepFactory.create(seq));
	}
	
	private void addToFlow(boolean started, FlowBuilder<SimpleFlow> flowBuilder, SimpleFlow flow) {
		if( started ) flowBuilder.next(flow);
		else flowBuilder.start(flow);
	}
	
	private void addToFlow(boolean started, FlowBuilder<SimpleFlow> flowBuilder, Step step) {
		if( started ) flowBuilder.next(step);
		else flowBuilder.start(step);
	}
}
