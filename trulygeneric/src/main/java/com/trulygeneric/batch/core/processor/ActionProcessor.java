package com.trulygeneric.batch.core.processor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;

import com.trulygeneric.batch.action.behaviour.IAction;
import com.trulygeneric.common.pojo.RecordMap;

public class ActionProcessor implements ItemProcessor< RecordMap<Object>, RecordMap<Object>> {
	
	private List<IAction<RecordMap<Object>>> actions;
	
	public ActionProcessor() {
		this.actions = new ArrayList<>();
	}
	
	public void action( IAction<RecordMap<Object>> action ) {
		actions.add(action);
	}
	
	@Override
	public RecordMap<Object> process(RecordMap<Object> data) throws Exception {
		for( IAction<RecordMap<Object>> action : actions )
			action.act(data);
		return data;
	}
	
}
