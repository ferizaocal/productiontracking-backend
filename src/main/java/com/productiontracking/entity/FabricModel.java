package com.productiontracking.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "fabric_models")
public class FabricModel extends BaseEntity {
    private Long fabricBrandId;
    private String name;
    private String status = Status.ACTIVE.toString();

    public enum Status {
        ACTIVE,
        INACTIVE
    }
}
