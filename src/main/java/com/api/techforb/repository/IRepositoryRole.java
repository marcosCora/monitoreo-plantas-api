package com.api.techforb.repository;

import com.api.techforb.entity.Role;
import com.api.techforb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRepositoryRole extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String nameRole);
}
