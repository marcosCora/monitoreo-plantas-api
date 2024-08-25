package com.api.techforb.service;

import com.api.techforb.dtos.DtoPlant;
import com.api.techforb.dtos.DtoReadingsTotals;
import com.api.techforb.entity.Plant;

import java.util.List;

public interface IServicePlant {

    public List<Plant> getAllPlants() throws Exception;
    public List<DtoPlant> getAllPlantsDto() throws Exception;
    public String savePlant(DtoPlant plant)throws Exception;
    public String savePlant(Plant plant) throws Exception;
    public String deletePlant(Long id) throws Exception;
    public String updatePlant(Long id, Plant plantUpdate) throws Exception;
    public DtoReadingsTotals readingsTotals();
    public String updatePlant(DtoPlant dtoPlant) throws Exception;
}
