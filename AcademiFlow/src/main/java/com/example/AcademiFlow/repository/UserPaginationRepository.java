package com.example.AcademiFlow.repository;

import com.example.AcademiFlow.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPaginationRepository extends PagingAndSortingRepository<Users, Long> {

    @Query("SELECT u FROM Users u WHERE (u.name LIKE %:filter% OR u.email LIKE %:filter%) AND u.isDeleted = false")
    Page<Users> findAllWithFilter(Pageable pageable, @Param("filter") String filter);

    @Query("SELECT u FROM Users u WHERE u.isDeleted = false ORDER BY u.name")
    Page<Users> findAllByName(Pageable pageable);

    @Query("SELECT u FROM Users u WHERE u.isDeleted = false ORDER BY u.email")
    Page<Users> findAllByEmail(Pageable pageable);
}
