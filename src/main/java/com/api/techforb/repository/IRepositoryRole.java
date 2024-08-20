package com.api.techforb.repository;

import com.api.techforb.entity.Role;
import com.api.techforb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRepositoryRole extends JpaRepository<Role, Long> {

    @Query(value = "SELECT r FROM  roles r WHERE r.name = :nameRole", nativeQuery = true)
    Optional<Role> findByName(@Param("nameRole") String nameRole);
}
