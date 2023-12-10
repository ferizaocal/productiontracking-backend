package com.productiontracking.service;

import com.productiontracking.dto.request.CreateProductionModel;
import com.productiontracking.dto.response.ServiceResponse;
import com.productiontracking.entity.ProductionModel;

public interface ProductionModelService {
    ServiceResponse<ProductionModel> findAll();

    ServiceResponse<ProductionModel> create(CreateProductionModel productionModel);

    ServiceResponse<ProductionModel> update(ProductionModel productionModel);
}
