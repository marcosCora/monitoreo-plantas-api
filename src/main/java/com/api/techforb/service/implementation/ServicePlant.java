package com.api.techforb.service.implementation;

import com.api.techforb.dtos.DtoPlant;
import com.api.techforb.entity.Plant;
import com.api.techforb.mapper.PlantMapper;
import com.api.techforb.repository.IRepositoryPlant;
import com.api.techforb.service.IServicePlant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServicePlant implements IServicePlant {

    @Autowired
    private IRepositoryPlant plantRepository;
    @Autowired
    private PlantMapper plantMapper;


    @Override
    public List<Plant> getAllPlants() throws Exception{
        List<Plant> plants = plantRepository.findAll();
        if(plants.isEmpty()){
            throw new Exception("Plants Not Fund");
        }
        return plants;/*.stream()
                .map(plantMapper::plantToDto)
                .collect(Collectors.toList());*/
    }

    @Override
    public String savePlant(Plant plant){
        plantRepository.save(plant);
        return "Plant Saved";
    }

    @Override
    public String deletePlant(Long id) throws Exception{
        plantRepository.findById(id)
                .orElseThrow(()-> new Exception("Plant not found"));
        plantRepository.deleteById(id);
        return "Plant deleted";
    }

    @Override
    public String updatePlant(Long id, Plant plantUpdate) throws Exception{
        Plant plant = plantRepository.findById(id)
                .orElseThrow(() -> new Exception("Plant not found"));
        plantRepository.save(plantUpdate);
        return "Plant Updated";
    }






}
