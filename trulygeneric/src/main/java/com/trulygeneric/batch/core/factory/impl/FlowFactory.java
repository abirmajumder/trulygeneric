package com.trulygeneric.batch.core.factory.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trulygeneric.batch.constants.JobAction;
import com.trulygeneric.batch.core.delegate.IStepFlowDelegator;
import com.trulygeneric.batch.core.factory.behaviour.IFlowFactory;
import com.trulygeneric.batch.core.factory.behaviour.IStepFactory;
import com.trulygeneric.batch.datamodel.entity.GenericJob;
import com.trulygeneric.batch.datamodel.entity.JobSequence;
import com.trulygeneric.batch.datamodel.entity.StepSequence;
import com.trulygeneric.batch.datamodel.repository.GenericJobRepository;
import com.trulygeneric.batch.datamodel.repository.JobSequenceRepository;
import com.trulygeneric.batch.datamodel.repository.StepSequenceRepository;
import com.trulygeneric.common.constants.Message;
import com.trulygeneric.common.constants.SysCommon;
import com.trulygeneric.common.exception.ApplicationException;
import com.trulygeneric.common.pojo.RecordMap;
import com.trulygeneric.common.util.NumberUtil;
import com.trulygeneric.common.util.ParseUtil;
import com.trulygeneric.batch.core.builder.ProcessBuilder;

@Component
public class FlowFactory implements IFlowFactory {

	@Autowired private IStepFactory stepFactory;
	@Autowired private GenericJobRepository jobRepo;
	@Autowired private JobSequenceRepository jobSeqRepo;
	@Autowired private StepSequenceRepository stepSeqRepo;
	@Autowired private IStepFlowDelegator delegator;
	
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
			delegator.inflictJobSequence(started, flowBuilder, sequences, i, count);
			i += count;
			started = true;
		}
		flowBuilder.end();
		return flowBuilder.build();
	}
	
	private String assertFlowStateForProcessPart( StepSequence seq, boolean processEnded ) throws ApplicationException {
		if( JobAction.isProcessPart( seq.getStepName() ) ) {
			if( processEnded )
				throw new ApplicationException(Message.NO_STP_PRCS);
			return seq.getStepName();
		}
		return null;
	}
	
	@Override
	public SimpleFlow createProcessFlow(JobSequence seq) throws Exception {
		Map<String,String> params = ParseUtil.parseParamStr(seq.getStepParams());
		final String processName = StringUtils.substringBefore(params.get("name"), SysCommon.DOT).toUpperCase() ;
		List<StepSequence> sequences = this.stepSeqRepo.findByjobSequenceIdAndActiveOrderBySequenceAsc(seq.getId(), SysCommon.Y);
		FlowBuilder<SimpleFlow> flowBuilder = new FlowBuilder<>( processName+ SysCommon.USR + SysCommon.FLW );
		ProcessBuilder<RecordMap<String>,RecordMap<Object>> processBuilder = new ProcessBuilder<>(stepBuilderFactory, params.get("name"), chunkSize);
		boolean started = false, processStarted = false, processEnded = false;
		
		for( int i =0; i<sequences.size(); i++ ) {
			String processPart = assertFlowStateForProcessPart(sequences.get(i), processEnded);
			int count = 1;
			while( ( i + count ) < sequences.size() && 
					NumberUtil.equals( Math.round(sequences.get(i).getSequence()), Math.round(sequences.get(i+count).getSequence())) ) {
				if( null == processPart )
					processPart = assertFlowStateForProcessPart(sequences.get(i + count), processEnded);
				count++;
			}
			if( null == processPart ) {
				for( int j=0; j < count ; j++)
					delegator.inflictFlow(started, flowBuilder, stepFactory.create(sequences.get(j)));
				processEnded = processStarted;
			} else {
				//delegator to ChunkStepBuilder
			}
			started = true;
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

}
