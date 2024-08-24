package com.api.techforb.service.implementation;

import com.api.techforb.dtos.DtoPlant;
import com.api.techforb.dtos.DtoReadingsTotals;
import com.api.techforb.entity.Plant;
import com.api.techforb.entity.Reading;
import com.api.techforb.mapper.PlantMapper;
import com.api.techforb.repository.IRepositoryPlant;
import com.api.techforb.service.IServicePlant;
import com.api.techforb.service.IServiceReading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServicePlant implements IServicePlant {

    @Autowired
    private IRepositoryPlant plantRepository;
    @Autowired
    private PlantMapper plantMapper;
    @Autowired
    private IServiceReading serviceReading;


    @Override
    public List<Plant> getAllPlants() throws Exception{
        List<Plant> plants = plantRepository.findAll();
        if(plants.isEmpty()){
            throw new Exception("Plants Not Fund");
        }

        return plants;
    }

    @Override
    public List<DtoPlant> getAllPlantsDto() throws Exception{
        List<Plant> plants = plantRepository.findAll();
        if(plants.isEmpty()){
            throw new Exception("Plants Not Fund");
        }
        List<DtoPlant> arrayDto = new ArrayList<>();
        for (Plant p : plants){
            System.out.println("Entra al for");
            DtoPlant dto = new DtoPlant();
            dto = plantMapper.plantToDto(p);
            dto = serviceReading.readingByPlant(dto);
            arrayDto.add(dto);
        }

        return arrayDto;
    }

    @Override
    public String savePlant(Plant plant)throws Exception{
        if(plant == null){
            throw new Exception("Plant not found");
        }
        Plant plantNew = new Plant();
        plantNew.setName(plant.getName());
        plantNew.setCountry(plant.getCountry());
        plantNew.setUrlFlag(plant.getUrlFlag());
        plantNew.setSensorsDisiable(plant.getSensorsDisiable());
        Plant plantSaved = plantRepository.save(plantNew);
        List<Reading> readings = plant.getReadings();
        System.out.println(plant.getReadings());
        for(Reading r : readings){
            r.setPlant(plantSaved);
        }
        serviceReading.saveReading(readings);
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

    @Override
    public String updatePlant(DtoPlant dtoPlant) throws Exception{

        Plant plant = plantRepository.findById(dtoPlant.getIdPlant()).get();
        if(plant == null){
            throw new Exception("Plant Not Found");
        }
        /*List<Long> idReadings = new ArrayList<>();
        for (Reading r : plant.getReadings()){
            idReadings.add(r.getIdReading());
        }
        if(idReadings.size() >0){
            serviceReading.deleteReadings(idReadings);
        }*/

        Plant plantSave = plantMapper.dtoToPlant(dtoPlant);
        plantRepository.save(plantSave);
        serviceReading.createReadingRandom(plantSave, dtoPlant.getCantReadings(),
                dtoPlant.getCantReadingOk(), dtoPlant.getCantAlertMedium(),
                dtoPlant.getCantAlertRed());

        //plantRepository.save(plantSave);
        return "Plant saved";
    }

    @Override
    public DtoReadingsTotals readingsTotals(){
        DtoReadingsTotals dto = serviceReading.readingsTotals();
        dto.setSensorsDisiabled(plantRepository.getTotalSensorsDisiabled());
        return dto;
    }






}
