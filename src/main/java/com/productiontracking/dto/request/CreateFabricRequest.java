package com.productiontracking.dto.request;

import lombok.Data;

@Data
public class CreateFabricRequest {
    private Long fabricModelId;
    private Long unitId;
    private String name;
}
