package com.productiontracking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "operationId", referencedColumnName = "id")
    private RoleOperation roleOperation;

    public Role(String name) {
        this.name = name;

    }

    public Role() {
        super();
    }

    public enum RoleName {
        SUPERADMIN,
        ADMIN
    }

    public enum Status {
        ACTIVE,
        INACTIVE
    }
}
