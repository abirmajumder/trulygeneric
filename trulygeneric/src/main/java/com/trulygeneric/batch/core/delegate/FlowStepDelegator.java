package com.trulygeneric.batch.core.delegate;

import java.util.List;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trulygeneric.batch.constants.JobAction;
import com.trulygeneric.batch.core.factory.behaviour.IFlowFactory;
import com.trulygeneric.batch.core.factory.behaviour.IStepFactory;
import com.trulygeneric.batch.datamodel.entity.JobSequence;

@Component
public class FlowStepDelegator implements IStepFlowDelegator {
	
	@Autowired private IFlowFactory flowFactory;
	@Autowired private IStepFactory stepFactory;
	
	@Override
	public void inflictJobSequence(boolean started, FlowBuilder<SimpleFlow> flowBuilder, List<JobSequence> sequences, int i, int count) throws Exception {
		if( count > 1 )
			inflictFlow( started, flowBuilder, flowFactory.createSplit(sequences, i, count));
		else 
			inflictFlow(started, flowBuilder, sequences.get(i));
	}
	
	private void inflictFlow( boolean started, FlowBuilder<SimpleFlow> flowBuilder, JobSequence seq) throws Exception {
		if( JobAction.get(seq.getStepName()).isProcess() )
			inflictFlow(started, flowBuilder, flowFactory.createProcessFlow(seq));
		else 
			inflictFlow(started, flowBuilder, stepFactory.create(seq));
	}
	
	@Override
	public void inflictFlow(boolean started, FlowBuilder<SimpleFlow> flowBuilder, SimpleFlow flow) {
		if( started ) flowBuilder.next(flow);
		else flowBuilder.start(flow);
	}
	
	@Override
	public void inflictFlow(boolean started, FlowBuilder<SimpleFlow> flowBuilder, Step step) {
		if( started ) flowBuilder.next(step);
		else flowBuilder.start(step);
	}
	
}
