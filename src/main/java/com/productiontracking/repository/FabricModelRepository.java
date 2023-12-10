package com.productiontracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productiontracking.entity.FabricModel;

@Repository
public interface FabricModelRepository extends JpaRepository<FabricModel, Long> {

}
