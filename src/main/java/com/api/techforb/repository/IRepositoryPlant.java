package com.api.techforb.repository;

import com.api.techforb.entity.Plant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryPlant extends JpaRepository<Plant, Long> {

    @Query(value = "SELECT SUM(COALESCE(sensors_disiable, 0)) AS total_sensors_disabled\n" +
            "FROM plants", nativeQuery = true)
    public Long getTotalSensorsDisiabled();

}
