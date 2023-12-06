package com.productiontracking.dto.request;

import lombok.Data;

@Data
public class ProductionModelDto {
    private Long branchId;
    private String productionName;
    private String productionImage;
    private String imageContentType;
}
