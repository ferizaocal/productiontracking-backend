package com.productiontracking.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "fabrics")
public class Fabric extends BaseEntity {
    private Long fabricModelId;
    private Long productionModelId;
    private Long unitId;
    private String name;
    private String status = Status.ACTIVE.toString();

    private enum Status {
        ACTIVE,
        INACTIVE
    }
}
