package com.productiontracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.productiontracking.entity.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query(value = "SELECT  u.id as id,  u.first_name as firstName,  u.last_name as lastName,  u.email as email,  u.phone as phone,  DATE_FORMAT(u.created_date, '%d.%m.%Y %h:%m') as createdDate FROM  user_roles ur LEFT JOIN users u ON  u.id = ur.user_id  LEFT JOIN role_operations ro ON ro.role_id = ur.role_id   LEFT JOIN operations o ON o.id = ro.operation_id   WHERE   ur.role_id = :roleId AND o.production_model_id  = :productionModelId ", nativeQuery = true)
    public List<com.productiontracking.model.UserRole> findByRoleIdAndProductionModelId(@Param("roleId") Long roleId,
            @Param("productionModelId") Long productionModelId);
}
