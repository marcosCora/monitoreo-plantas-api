package com.api.techforb.repository;

import com.api.techforb.entity.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRepositoryAlert extends JpaRepository<Alert, Long> {


    @Query(value = "SELECT COUNT(*) FROM alerts WHERE alerts.type_alert = :type_alert", nativeQuery = true)
    public Long getTotalAlertByType(@Param("type_alert") String type_alert);

}
