package com.productiontracking.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.productiontracking.dto.request.CreateFabricBrandRequest;
import com.productiontracking.dto.request.UpdateFabricBrandRequest;
import com.productiontracking.dto.response.FabricBrandResponse;
import com.productiontracking.dto.response.FabricBrandWithModelResponse;
import com.productiontracking.dto.response.ServiceResponse;
import com.productiontracking.mapper.ModelMapperService;
import com.productiontracking.repository.FabricBrandRepository;

public class FabricBrandServiceImpl implements FabricBrandService {

    @Autowired
    ModelMapperService modelMapperService;

    private FabricBrandRepository fabricBrandRepository;

    public FabricBrandServiceImpl(FabricBrandRepository fabricBrandRepository) {
        super();
        this.fabricBrandRepository = fabricBrandRepository;
    }

    @Override
    public ServiceResponse<FabricBrandResponse> create(CreateFabricBrandRequest request) {
        ServiceResponse<FabricBrandResponse> response = new ServiceResponse<>();
        try {
            response.setIsSuccessful(true);
        } catch (Exception e) {
            response.setExceptionMessage(e.getMessage())
                    .setHasExceptionError(true);

        }
        return response;
    }

    @Override
    public ServiceResponse<FabricBrandResponse> update(UpdateFabricBrandRequest request) {
        ServiceResponse<FabricBrandResponse> response = new ServiceResponse<>();
        try {
            response.setIsSuccessful(true);
        } catch (Exception e) {
            response.setExceptionMessage(e.getMessage())
                    .setHasExceptionError(true);

        }
        return response;
    }

    @Override
    public ServiceResponse<FabricBrandWithModelResponse> findById(Long id) {
        ServiceResponse<FabricBrandWithModelResponse> response = new ServiceResponse<>();
        try {
            response.setIsSuccessful(true);
        } catch (Exception e) {
            response.setExceptionMessage(e.getMessage())
                    .setHasExceptionError(true);

        }
        return response;
    }

    @Override
    public ServiceResponse<FabricBrandWithModelResponse> findAll(Long id) {
        ServiceResponse<FabricBrandWithModelResponse> response = new ServiceResponse<>();
        try {
            response.setIsSuccessful(true);
        } catch (Exception e) {
            response.setExceptionMessage(e.getMessage())
                    .setHasExceptionError(true);

        }
        return response;
    }

}
