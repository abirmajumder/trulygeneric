package com.trulygeneric.batch.datamodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trulygeneric.batch.datamodel.entity.GenericJob;

@Repository
public interface GenericJobRepository extends JpaRepository<GenericJob,Integer> {

	GenericJob findByJobNameAndActive(String jobName, String active);
	
}