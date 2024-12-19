package com.example.AcademiFlow.repository;

import com.example.AcademiFlow.entity.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface TechnoogyRepository extends JpaRepository<Technology,Long> {

    @Query(value = "SELECT t FROM Technology t WHERE t.technologyName IN :names")
    public Set<Technology> findByTechnologyName(Set<String> names);
}
