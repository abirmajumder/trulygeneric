package com.trulygeneric.batch.action;

import java.util.Map;

import com.trulygeneric.batch.action.behaviour.IAction;
import com.trulygeneric.batch.action.impl.QueryAction;
import com.trulygeneric.batch.action.impl.SpAction;
import com.trulygeneric.common.constants.ActionType;
import com.trulygeneric.common.constants.Message;
import com.trulygeneric.common.exception.ApplicationException;
import com.trulygeneric.common.pojo.RecordMap;

public class ActionFactory {
	
	public static IAction<RecordMap<Object>> create( String name, Map<String,String> details ) throws ApplicationException {
		IAction<RecordMap<Object>> action = null;
		switch( ActionType.get(name) )  {
			case SP: new SpAction(details); break;	
			case QUERY : new QueryAction(details); break;
			case NONE: throw new ApplicationException(  String.format(Message.NO_ACTN, name) );
		}
		return action;
	}
}
