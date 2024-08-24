package com.api.techforb.mapper;

import com.api.techforb.dtos.DtoPlant;
import com.api.techforb.entity.Plant;
import com.api.techforb.entity.Reading;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PlantMapper {

    public DtoPlant plantToDto(Plant plant){
        DtoPlant dto = new DtoPlant();
        dto.setIdPlant(plant.getIdPlant());
        dto.setName(plant.getName());
        dto.setCountry(plant.getCountry());
        dto.setUrlFlag(plant.getUrlFlag());
        dto.setSensorsDisiabled(plant.getSensorsDisiable());
        return dto;
    }

    public Plant dtoToPlant(DtoPlant dto){
        Plant plant = new Plant();
        plant.setIdPlant(dto.getIdPlant());
        plant.setName(dto.getName());
        plant.setCountry(dto.getCountry());
        plant.setUrlFlag(dto.getUrlFlag());
        plant.setSensorsDisiable(dto.getSensorsDisiabled());
        plant.setReadings(new ArrayList<Reading>());
        return plant;

    }

}
