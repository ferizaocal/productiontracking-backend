package com.productiontracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productiontracking.entity.Operation;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    Operation findByOperationNameAndIsDeleted(String operationName, Boolean isDeleted);

    List<Operation> findAllByStatus(String status);
}
