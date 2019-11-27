package com.trulygeneric.batch.datamodel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trulygeneric.batch.datamodel.entity.StepSequence;

@Repository
public interface StepSequenceRepository extends JpaRepository<StepSequence,Integer> {
	List<StepSequence> findByjobSequenceIdAndActiveOrderBySequenceAsc( Integer jobSequenceId, String active );
}