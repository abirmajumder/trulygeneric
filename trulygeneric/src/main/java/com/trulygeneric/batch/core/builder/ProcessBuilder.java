package com.trulygeneric.batch.core.builder;

import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.builder.FaultTolerantStepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;

public class ProcessBuilder<I,O> {
	
	private FaultTolerantStepBuilder<I,O> stepBuilder;

	public ProcessBuilder(StepBuilderFactory stepBuilderFactory, String name, int chunkSize) {
		this.stepBuilder = stepBuilderFactory.get(name).<I,O>chunk(chunkSize).faultTolerant();
	}
	
	public ProcessBuilder<I,O> reader( ItemReader<I> reader ) {
		this.stepBuilder.reader(reader);
		return this;
	}
	
	public ProcessBuilder<I,O> whiter( ItemWriter<O> writer ) {
		this.stepBuilder.writer(writer);
		return this;
	}
	
	
}
