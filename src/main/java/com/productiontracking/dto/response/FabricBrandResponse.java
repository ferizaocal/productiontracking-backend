package com.productiontracking.dto.response;

import lombok.Data;

@Data
public class FabricBrandResponse extends BaseResponse {
    private Long productionModelId;
    private String name;
    private String status;
}
