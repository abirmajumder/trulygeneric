package com.trulygeneric.common.constants;

import java.util.stream.Stream;

public enum ActionType {
	SP, QUERY, NONE;
	
	public static ActionType get( String name ) {
		return 
			Stream.of( values() )
			  .filter( at -> at.name().equals(name) )
			  .findFirst().orElse(NONE);
	}
}
