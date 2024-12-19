package com.example.AcademiFlow.repository;

import com.example.AcademiFlow.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsersRepository extends JpaRepository<Users,Long> {

    public Users findUserByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE set_password_key = ?1",nativeQuery = true)
    Users findUserByUUID(String key);
}
