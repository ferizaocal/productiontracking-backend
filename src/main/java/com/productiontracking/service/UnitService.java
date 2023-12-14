package com.productiontracking.service;

import com.productiontracking.dto.request.CreateUnitRequest;
import com.productiontracking.dto.response.ServiceResponse;
import com.productiontracking.dto.response.UnitResponse;

public interface UnitService {
    ServiceResponse<UnitResponse> createUnit(CreateUnitRequest createUnitRequest);

    ServiceResponse<UnitResponse> getUnitById(Long id);

    ServiceResponse<UnitResponse> findAll();

}
