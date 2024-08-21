package com.api.techforb.mapper;

import com.api.techforb.dtos.DtoPlant;
import com.api.techforb.entity.Plant;
import org.springframework.stereotype.Component;

@Component
public class PlantMapper {

    public DtoPlant plantToDto(Plant plant){
        DtoPlant dto = new DtoPlant();
        dto.setIdPlant(plant.getIdPlant());
        dto.setName(plant.getName());
        dto.setNameCountry(plant.getCountry().getName());
        dto.setSensorsDisiabled(plant.getSensorsDisiable());
        return dto;
    }

}
