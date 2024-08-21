package com.api.techforb.service;

import com.api.techforb.dtos.DtoPlant;
import com.api.techforb.entity.Plant;

import java.util.List;

public interface IServicePlant {

    public List<Plant> getAllPlants() throws Exception;
    public String savePlant(Plant plant);
    public String deletePlant(Long id) throws Exception;
    public String updatePlant(Long id, Plant plantUpdate) throws Exception;

}
