package com.trulygeneric.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BatchConfig {
	
	@Value("${step.process.chunksize}")
	public Integer defaultChunkSize;
	
}
