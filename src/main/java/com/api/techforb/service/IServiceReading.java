package com.api.techforb.service;

import com.api.techforb.dtos.DtoPlant;
import com.api.techforb.dtos.DtoReadingsTotals;
import com.api.techforb.entity.Plant;
import com.api.techforb.entity.Reading;
import lombok.Locked;

import java.util.List;

public interface IServiceReading {
    public List<Reading> getAllReading();
    public String saveReading(Reading reading);
    public String deleteReading(Long id) throws Exception;
    public void saveReading(List<Reading> readings);
    public DtoReadingsTotals readingsTotals();
    public DtoPlant readingByPlant(DtoPlant dto);
}
