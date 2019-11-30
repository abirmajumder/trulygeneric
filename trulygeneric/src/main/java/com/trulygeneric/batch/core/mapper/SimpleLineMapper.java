package com.trulygeneric.batch.core.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.stereotype.Component;

import com.trulygeneric.common.pojo.RecordMap;

@Component
public class SimpleLineMapper implements LineMapper<RecordMap<String>> {
	
	private List<String> headers;

	public SimpleLineMapper() {
		this.headers = new ArrayList<String>();
	}
	
	private void initHeader( String line ) {
		
	}

	@Override
	public RecordMap<String> mapLine(String line, int index) throws Exception {
		if( 0 == index ) initHeader(line); 
		return null;
	}

}
