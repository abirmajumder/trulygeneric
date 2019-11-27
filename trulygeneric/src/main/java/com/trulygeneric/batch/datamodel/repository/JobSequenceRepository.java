package com.trulygeneric.batch.datamodel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trulygeneric.batch.datamodel.entity.JobSequence;

@Repository
public interface JobSequenceRepository extends JpaRepository<JobSequence,Integer> {
	List<JobSequence> findByJobIdAndActiveOrderBySequenceAsc( Integer jobId, String active );
}