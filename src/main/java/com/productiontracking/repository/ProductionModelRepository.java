package com.productiontracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productiontracking.entity.ProductionModel;

@Repository
public interface ProductionModelRepository extends JpaRepository<ProductionModel, Long> {

    ProductionModel findByName(String name);
}
