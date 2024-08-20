package com.api.techforb.repository;

import com.api.techforb.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryReading extends JpaRepository<Reading, Long> {
}
