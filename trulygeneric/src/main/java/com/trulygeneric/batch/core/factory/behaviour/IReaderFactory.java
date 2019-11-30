package com.trulygeneric.batch.core.factory.behaviour;

import org.springframework.batch.item.ItemReader;

import com.trulygeneric.batch.datamodel.entity.JobSequence;

public interface IReaderFactory<T> {
	ItemReader<T> create( JobSequence jobSeq ) throws Exception;
}
