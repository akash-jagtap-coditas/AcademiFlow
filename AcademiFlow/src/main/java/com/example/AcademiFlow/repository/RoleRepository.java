package com.example.AcademiFlow.repository;

import com.example.AcademiFlow.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query(value = "SELECT * FROM roles WHERE role_name = ?1",nativeQuery = true)
    Role findByRoleName(String role);
}
