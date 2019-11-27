package com.trulygeneric.batch.datamodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trulygeneric.batch.datamodel.entity.Transformation;

@Repository
public interface TransformationRepository extends JpaRepository<Transformation,Integer> {
	
}