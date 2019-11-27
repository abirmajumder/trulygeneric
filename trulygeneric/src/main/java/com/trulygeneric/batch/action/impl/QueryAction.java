package com.trulygeneric.batch.action.impl;

import java.util.Map;

import com.trulygeneric.batch.action.behaviour.IAction;
import com.trulygeneric.common.pojo.RecordMap;

public class QueryAction implements IAction<RecordMap<Object>> {
	
	private String query, dataSource;
	
	public QueryAction( Map<String,String> details ) {
		this.query = details.get("query");
		this.dataSource = details.get("ds");
	}
	
	@Override
	public RecordMap<Object> act(RecordMap<Object> data) throws Exception {
		System.out.println("QUERY ACTION CALLED : With " + query + " - " + dataSource);
		if( null != data )
			data.put("action", "QUERY");
		return data;
	}

}
