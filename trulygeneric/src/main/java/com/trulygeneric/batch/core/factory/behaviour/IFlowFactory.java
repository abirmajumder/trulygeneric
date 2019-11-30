package com.trulygeneric.batch.core.factory.behaviour;

import java.util.List;

import org.springframework.batch.core.job.flow.support.SimpleFlow;

import com.trulygeneric.batch.datamodel.entity.JobSequence;
import com.trulygeneric.batch.datamodel.entity.StepSequence;

public interface IFlowFactory {
	SimpleFlow create( String jobName ) throws Exception;
	SimpleFlow createSplit( List<JobSequence> sequences, int index, int spread ) throws Exception;
	SimpleFlow createComposite( List<StepSequence> sequences, int index, int spread ) throws Exception;
	SimpleFlow createProcessFlow(JobSequence seq) throws Exception;
}
