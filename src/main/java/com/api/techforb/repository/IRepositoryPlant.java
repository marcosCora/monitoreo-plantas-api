package com.api.techforb.repository;

import com.api.techforb.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryPlant extends JpaRepository<Plant, Long> {
}
