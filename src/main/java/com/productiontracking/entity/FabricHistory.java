package com.productiontracking.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "fabric_histories")
public class FabricHistory extends BaseEntity {
    private Long fabricId;
    private Type type;
    private Double quantity;

    public enum Type {
        IN,
        OUT
    }
}
