package com.api.techforb.repository;

import com.api.techforb.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryReading extends JpaRepository<Reading, Long> {

    @Query(value = "SELECT COUNT(*) FROM readings")
    public Long getTotalReadings();
    @Query(value = "SELECT COUNT(*) FROM readings where readings.fk_plant = :fk_plant", nativeQuery = true)
    public Long getReadingByPlant(@Param("fk_plant") Long fk_plant);
}
