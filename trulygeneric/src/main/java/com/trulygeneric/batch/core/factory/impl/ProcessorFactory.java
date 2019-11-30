package com.trulygeneric.batch.core.factory.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.trulygeneric.batch.action.ActionFactory;
import com.trulygeneric.batch.core.factory.behaviour.IProcessorFactory;
import com.trulygeneric.batch.core.processor.ActionProcessor;
import com.trulygeneric.batch.datamodel.entity.StepSequence;
import com.trulygeneric.common.util.ParseUtil;

@Component
public class ProcessorFactory implements IProcessorFactory {

	@Override
	public ActionProcessor create( List<StepSequence> sequences ) throws Exception {
		ActionProcessor processor = new ActionProcessor();
		for ( StepSequence seq : sequences ) {
			processor.action( ActionFactory.create(seq.getStepName(), ParseUtil.parseParamStr(seq.getStepParams())) );
		}
		return processor;
	}

}
