package com.trulygeneric.batch.accumulator.factory.behaviour;

import org.springframework.batch.core.Step;

import com.trulygeneric.batch.datamodel.entity.JobSequence;
import com.trulygeneric.batch.datamodel.entity.StepSequence;

public interface IStepFactory {
	Step create( StepSequence stepSeq ) throws Exception;
	Step create( JobSequence stepSeq ) throws Exception;
}
