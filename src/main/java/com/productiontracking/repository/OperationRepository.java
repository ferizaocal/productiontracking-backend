package com.productiontracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.firebase.database.core.operation.Operation;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

}
