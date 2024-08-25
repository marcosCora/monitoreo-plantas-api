package com.api.techforb.service;

import com.api.techforb.Enums.TypeAlert;
import com.api.techforb.Enums.TypeReadings;
import com.api.techforb.dtos.DtoPlant;
import com.api.techforb.dtos.DtoReadingsTotals;
import com.api.techforb.entity.Alert;
import com.api.techforb.entity.Plant;
import com.api.techforb.entity.Reading;
import lombok.Locked;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public interface IServiceReading {
    public List<Reading> getAllReading();
    public String saveReading(Reading reading);
    public String deleteReading(Long id) throws Exception;
    public String deleteReadings(List<Long> ids) throws Exception;
    public void saveReading(List<Reading> readings);
    public DtoReadingsTotals readingsTotals();
    public DtoPlant readingByPlant(DtoPlant dto);
    public void createReadingRandom(Plant p, Long cantReading, Long alertOk,
                                    Long alertMedium, Long alertRed);
    public TypeReadings typeReadingRandom();
}
