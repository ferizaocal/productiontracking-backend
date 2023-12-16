package com.productiontracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productiontracking.entity.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

}
