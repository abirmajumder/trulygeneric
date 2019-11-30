package com.trulygeneric.batch.core.builder;

import io.micrometer.core.instrument.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.builder.FaultTolerantStepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.trulygeneric.batch.constants.JobAction;
import com.trulygeneric.batch.core.factory.behaviour.IProcessorFactory;
import com.trulygeneric.batch.datamodel.entity.StepSequence;
import com.trulygeneric.batch.policy.NamingPolicy;
import com.trulygeneric.common.exception.ApplicationException;
import com.trulygeneric.common.pojo.RecordMap;
import com.trulygeneric.configuration.BatchConfig;

@Component
public class ProcessBuilder {
	
	@Autowired private BatchConfig batchConfig;
	@Autowired private StepBuilderFactory stepBuilderFactory;
	@Autowired private IProcessorFactory processorFactory;
	
	private Map<String,FaultTolerantStepBuilder<RecordMap<Object>,RecordMap<Object>>> objectMap;
	
	public ProcessBuilder() {
		this.objectMap = new HashMap<>(); 
	}
	
	public ProcessBuilder startBuild( Map<String,String> params ) {
		String processName =  NamingPolicy.processName(params);
		int chunkSize = batchConfig.defaultChunkSize;
		if( StringUtils.isNotEmpty( params.get("chunkSize") ) ) {
			chunkSize = Integer.parseInt(params.get("chunkSize"));
		}
		this.objectMap.put(processName, stepBuilderFactory.get(processName)
								.<RecordMap<Object>,RecordMap<Object>>chunk(chunkSize)
								.faultTolerant());
		return this;
	}
	
	public ProcessBuilder reader( String processName, ItemReader<RecordMap<Object>> reader ) {
		this.objectMap.get(processName).reader(reader);
		return this;
	}
	
	public ProcessBuilder writer( String processName, ItemWriter<RecordMap<Object>> writer ) {
		this.objectMap.get(processName).writer(writer);
		return this;
	}
	
	public Step build( String processName ) {
		return 
				this.objectMap.get(processName)
					.build();
	}

	public ProcessBuilder processAction(String processName, String processPart, List<StepSequence> sequences, int index, int spread) throws Exception {
		JobAction actn = JobAction.get(processName);
		switch( actn ) {
			case TRANSFORMATION: objectMap.get(processName).processor(this.processorFactory.create(sequences)); break;
			case WRITER:break;
			case NONE: throw new ApplicationException();
		}
		return this;
	}
	
}
