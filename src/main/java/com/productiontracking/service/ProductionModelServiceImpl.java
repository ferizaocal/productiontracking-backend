package com.productiontracking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productiontracking.dto.request.CreateProductionRequest;
import com.productiontracking.dto.response.ServiceResponse;
import com.productiontracking.entity.ProductionModel;
import com.productiontracking.entity.ProductionModel.Status;
import com.productiontracking.exception.NotFoundProductionModel;
import com.productiontracking.mapper.ModelMapperService;
import com.productiontracking.repository.ProductionModelRepository;

@Service
public class ProductionModelServiceImpl implements ProductionModelService {

    @Autowired
    private ModelMapperService modelMapperService;

    private ProductionModelRepository productionModelRepository;

    public ProductionModelServiceImpl(ProductionModelRepository productionModelRepository) {
        super();
        this.productionModelRepository = productionModelRepository;
    }

    @Override
    public ServiceResponse<ProductionModel> findAll() {
        ServiceResponse<ProductionModel> response = new ServiceResponse<>();
        try {
            List<ProductionModel> productionModels = productionModelRepository.findAll();
            response.setList(productionModels).setIsSuccessful(true);
        } catch (Exception e) {
            response.setExceptionMessage(e.getMessage().toString()).setHasExceptionError(true);
        }
        return response;
    }

    @Override
    public ServiceResponse<ProductionModel> create(CreateProductionRequest productionModel) {
        ServiceResponse<ProductionModel> response = new ServiceResponse<>();
        try {
            ProductionModel newProductionModel = modelMapperService.forRequest().map(productionModel,
                    ProductionModel.class);
            productionModelRepository.save(newProductionModel);
            response.setIsSuccessful(true).setEntity(newProductionModel);
        } catch (Exception e) {
            response.setExceptionMessage(e.getMessage().toString()).setHasExceptionError(true);
        }
        return response;
    }

    @Override
    public ServiceResponse<ProductionModel> update(ProductionModel productionModel) {
        ServiceResponse<ProductionModel> response = new ServiceResponse<>();
        try {
            ProductionModel existingProductionModel = productionModelRepository.findById(productionModel.getId())
                    .orElse(null);
            if (existingProductionModel == null) {
                throw new NotFoundProductionModel(productionModel.getId());
            }
            existingProductionModel.setName(productionModel.getName());
            existingProductionModel.setIcon(productionModel.getIcon());
            existingProductionModel.setStatus(productionModel.getStatus());
            productionModelRepository.save(existingProductionModel);
            response.setIsSuccessful(true).setEntity(existingProductionModel);
        } catch (Exception e) {
            response.setExceptionMessage(e.getMessage().toString()).setHasExceptionError(true);
        }
        return response;
    }

    @Override
    public ServiceResponse<ProductionModel> delete(Long id) {
        ServiceResponse<ProductionModel> response = new ServiceResponse<>();
        try {
            ProductionModel existingProductionModel = productionModelRepository.findById(id)
                    .orElse(null);
            if (existingProductionModel == null) {
                throw new NotFoundProductionModel(id);
            }
            existingProductionModel.setIsDeleted(true);
            productionModelRepository.save(existingProductionModel);
            response.setIsSuccessful(true).setEntity(existingProductionModel);
        } catch (Exception e) {
            response.setExceptionMessage(e.getMessage().toString()).setHasExceptionError(true);
        }
        return response;
    }

    @Override
    public ServiceResponse<ProductionModel> updateStatusById(Long id, Status status) {
        ServiceResponse<ProductionModel> response = new ServiceResponse<>();
        try {
            ProductionModel existingProductionModel = productionModelRepository.findById(id)
                    .orElse(null);
            if (existingProductionModel == null) {
                throw new NotFoundProductionModel(id);
            }
            existingProductionModel.setStatus(status.toString());
            productionModelRepository.save(existingProductionModel);
            response.setIsSuccessful(true).setEntity(existingProductionModel);
        } catch (Exception e) {
            response.setExceptionMessage(e.getMessage().toString()).setHasExceptionError(true);
        }
        return response;
    }

}
