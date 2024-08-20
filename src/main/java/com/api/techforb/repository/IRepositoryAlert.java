package com.api.techforb.repository;

import com.api.techforb.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryAlert extends JpaRepository<Alert, Long> {
}
