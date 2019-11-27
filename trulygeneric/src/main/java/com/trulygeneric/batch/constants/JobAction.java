package com.trulygeneric.batch.constants;

import java.util.stream.Stream;

public enum JobAction {
	
	SP,PROCESS,QUERY,TRANSFORMATION,WRITER,NONE;
	
	public static JobAction get( String name ) {
		return 
			Stream.of( values() )
			  .filter( at -> at.name().equals(name) )
			  .findFirst().orElse(NONE);
	}
	
	public boolean isProcess() {
		return this == PROCESS;
	}
	
}
