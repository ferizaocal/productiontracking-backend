package com.productiontracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productiontracking.entity.FabricHistory;

@Repository
public interface FabricHistoryRepository extends JpaRepository<FabricHistory, Long> {

}
