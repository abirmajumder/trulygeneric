package com.trulygeneric.batch.core.factory.impl;

import java.util.Map;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trulygeneric.batch.action.behaviour.IAction;
import com.trulygeneric.batch.action.impl.QueryAction;
import com.trulygeneric.batch.action.impl.SpAction;
import com.trulygeneric.batch.constants.JobAction;
import com.trulygeneric.batch.core.factory.behaviour.IStepFactory;
import com.trulygeneric.batch.datamodel.entity.JobSequence;
import com.trulygeneric.batch.datamodel.entity.StepSequence;
import com.trulygeneric.common.constants.Message;
import com.trulygeneric.common.exception.ApplicationException;
import com.trulygeneric.common.pojo.RecordMap;
import com.trulygeneric.common.util.ParseUtil;

@Component
public class StepFactory implements IStepFactory {
	
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
 
	@Override
	public Step create(StepSequence stepSeq) throws Exception {
		return null;
	}

	@Override
	public Step create(JobSequence jobSeq) throws Exception {
		IAction<RecordMap<Object>> stepAction = createStepAction( jobSeq.getStepName(), ParseUtil.parseParamStr(jobSeq.getStepParams())); 		 // SP, QUERY, PROCESS, FILESANITY
		return
			stepBuilderFactory
				.get(jobSeq.getStepName())
				.tasklet( (stepContribution, chunkContext) -> {
					stepAction.act(null);
					return RepeatStatus.FINISHED;
				})
				.build();
	}
	
	private IAction<RecordMap<Object>> createStepAction( String stepName, Map<String,String> params ) throws ApplicationException {
		JobAction action = JobAction.get( stepName );
		IAction<RecordMap<Object>> stepAction = null;
			switch( action ) {
				case QUERY: stepAction = new QueryAction( params );break;
				case SP: stepAction = new SpAction( params );break;
				default: throw new ApplicationException( String.format(Message.NO_STP_ACTN, stepName));
			}
		return stepAction;
	}

}
