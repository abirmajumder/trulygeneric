package com.trulygeneric.batch.datamodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trulygeneric.batch.datamodel.entity.DataField;

@Repository
public interface FieldRepository extends JpaRepository<DataField,Integer> {}