package com.trulygeneric.batch.accumulator.factory.impl;

import java.util.Map;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.trulygeneric.batch.accumulator.factory.behaviour.IReaderFactory;
import com.trulygeneric.batch.datamodel.entity.JobSequence;
import com.trulygeneric.common.pojo.RecordMap;
import com.trulygeneric.common.util.ParseUtil;

@Component
public class ReaderFactory implements IReaderFactory<RecordMap<String>> {

	@Override
	public ItemReader<RecordMap<String>> create(JobSequence jobSeq) throws Exception {
		Map<String, String> params = ParseUtil.parseParamStr( jobSeq.getStepParams() );
		String type = params.get("type");
		if( "file".equalsIgnoreCase(type) ) {
			return create( params.get("val"));
		} else
			throw new Exception("Unknown Type Provided For : " + jobSeq.getStepParams());
	}
	
	private FlatFileItemReader<RecordMap<String>> create( String fileName ) {
		LineMapper<RecordMap<String>> lineMapper = new LineMapper<RecordMap<String>>() {
			
			@Override
			public RecordMap<String> mapLine(String arg0, int arg1) throws Exception {
				return null;
			}
		};
		
		return new FlatFileItemReaderBuilder<RecordMap<String>>()
						.resource(new FileSystemResource(fileName))
						.lineMapper(lineMapper)
						.strict(true)
						.build();
	}

}
