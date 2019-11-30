package com.trulygeneric.batch.core.factory.behaviour;

import java.util.List;

import com.trulygeneric.batch.core.processor.ActionProcessor;
import com.trulygeneric.batch.datamodel.entity.StepSequence;

public interface IProcessorFactory {
	ActionProcessor create( List<StepSequence> sequences ) throws Exception;
}
