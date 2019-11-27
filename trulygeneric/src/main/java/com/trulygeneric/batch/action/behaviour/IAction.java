package com.trulygeneric.batch.action.behaviour;

public interface IAction<E> {
	E act( E data) throws Exception;
}
