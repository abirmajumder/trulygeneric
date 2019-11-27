package com.trulygeneric.batch.action.impl;

import java.util.Map;

import com.trulygeneric.batch.action.behaviour.IAction;
import com.trulygeneric.common.pojo.RecordMap;

public class SpAction implements IAction<RecordMap<Object>> {
	
	private String spName;
	
	public SpAction( Map<String,String> details ) {
		this.spName = details.get("name");
	}

	@Override
	public RecordMap<Object> act(RecordMap<Object> data) throws Exception {
		System.out.println("SP ACTION CALLED : SP Name " + this.spName);
		if( null != data ) {
			data.put("action", "SP");
		}
		return data;
	}

}
