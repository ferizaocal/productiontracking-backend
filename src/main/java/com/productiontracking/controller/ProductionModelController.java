package com.productiontracking.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.productiontracking.dto.request.CreateProductionRequest;
import com.productiontracking.dto.response.ServiceResponse;
import com.productiontracking.entity.ProductionModel;
import com.productiontracking.service.ProductionModelService;

@RestController
@RequestMapping(value = "/api/v1")
public class ProductionModelController {

    private ProductionModelService productionModelService;

    public ProductionModelController(ProductionModelService productionModelService) {
        super();
        this.productionModelService = productionModelService;
    }

    @RequestMapping(value = "/production-model", method = RequestMethod.POST)
    public ServiceResponse<?> create(@RequestBody CreateProductionRequest createProductionRequest) {
        return productionModelService.create(createProductionRequest);
    }

    @RequestMapping(value = "/production-model", method = RequestMethod.POST)
    public ServiceResponse<?> update(@RequestBody ProductionModel productionModel) {
        return productionModelService.update(productionModel);
    }

    @RequestMapping(value = "/production-model", method = RequestMethod.DELETE)
    public ServiceResponse<?> delete(@RequestBody Long id) {
        return productionModelService.delete(id);
    }

    @RequestMapping(value = "/production-model", method = RequestMethod.PUT)
    public ServiceResponse<?> updateStatusById(@RequestBody Long id, ProductionModel.Status status) {
        return productionModelService.updateStatusById(id, status);
    }

    @RequestMapping(value = "/production-models", method = RequestMethod.GET)
    public ServiceResponse<?> getAllProductionModels() {
        return productionModelService.findAll();
    }
}
