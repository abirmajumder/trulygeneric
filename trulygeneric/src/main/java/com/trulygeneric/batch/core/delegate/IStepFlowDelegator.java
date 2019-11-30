package com.trulygeneric.batch.core.delegate;

import java.util.List;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.support.SimpleFlow;

import com.trulygeneric.batch.datamodel.entity.JobSequence;

public interface IStepFlowDelegator {
	void inflictJobSequence(boolean started, FlowBuilder<SimpleFlow> flowBuilder, List<JobSequence> sequences, int i, int count) throws Exception;
	void inflictFlow(boolean started, FlowBuilder<SimpleFlow> flowBuilder, SimpleFlow flow);
	void inflictFlow(boolean started, FlowBuilder<SimpleFlow> flowBuilder, Step step);
}
