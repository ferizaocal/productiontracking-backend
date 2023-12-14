package com.productiontracking.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productiontracking.dto.request.CreateUnitRequest;
import com.productiontracking.dto.response.ServiceResponse;
import com.productiontracking.dto.response.UnitResponse;
import com.productiontracking.entity.Unit;
import com.productiontracking.mapper.ModelMapperService;
import com.productiontracking.repository.UnitRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UnitServiceImpl implements UnitService {

    @Autowired
    ModelMapperService modelMapperService;
    private UnitRepository unitRepository;

    public UnitServiceImpl(UnitRepository unitRepository) {
        super();
        this.unitRepository = unitRepository;
    }

    @Override
    public ServiceResponse<UnitResponse> createUnit(CreateUnitRequest createUnitRequest) {
        ServiceResponse<UnitResponse> response = new ServiceResponse<>();
        try {
            Unit unit = modelMapperService.forRequest().map(createUnitRequest, Unit.class);
            unit = unitRepository.save(unit);
            UnitResponse unitResponse = modelMapperService.forResponse().map(unit, UnitResponse.class);
            response.setIsSuccessful(true).setEntity(unitResponse);
        } catch (Exception e) {
            log.error("Error while creating unit", e);
            response.setHasExceptionError(false).setExceptionMessage("Error while creating unit");
        }
        return response;
    }

    @Override
    public ServiceResponse<UnitResponse> getUnitById(Long id) {
        ServiceResponse<UnitResponse> response = new ServiceResponse<>();
        try {
            Unit unit = unitRepository.findById(id).orElse(null);
            UnitResponse unitResponse = modelMapperService.forResponse().map(unit, UnitResponse.class);
            response.setIsSuccessful(true).setEntity(unitResponse);
        } catch (Exception e) {
            log.error("No unit found with id: {}", id, e);
            response.setHasExceptionError(false).setExceptionMessage("Error while creating unit");
        }
        return response;
    }

    @Override
    public ServiceResponse<UnitResponse> findAll() {
        ServiceResponse<UnitResponse> response = new ServiceResponse<>();
        try {
            List<Unit> units = unitRepository.findAll();
            List<UnitResponse> unitResponses = units.stream()
                    .map(unit -> modelMapperService.forResponse().map(unit, UnitResponse.class))
                    .collect(Collectors.toList());
            response.setIsSuccessful(true).setList(unitResponses);
        } catch (Exception e) {
            log.error("No units found", e);
            response.setHasExceptionError(false).setExceptionMessage("No units found");
        }
        return response;
    }

}
