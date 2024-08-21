package com.api.techforb.service.implementation;

import com.api.techforb.Enums.TypeAlert;
import com.api.techforb.dtos.DtoReadingsTotals;
import com.api.techforb.entity.Reading;
import com.api.techforb.repository.IRepositoryReading;
import com.api.techforb.service.IServiceAlert;
import com.api.techforb.service.IServiceReading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceReading implements IServiceReading {

    @Autowired
    private IRepositoryReading readingRepository;
    @Autowired
    private IServiceAlert serviceAlert;

    @Override
    public List<Reading> getAllReading() {
        return readingRepository.findAll();
    }

    @Override
    public String saveReading(Reading reading) {
        readingRepository.save(reading);
        return "Reading Saved";
    }

    @Override
    public void saveReading(List<Reading> readings){
        /*if(readings.isEmpty()){
            //tirar excp
        }*/
        System.out.println(readings);
        readingRepository.saveAll(readings);
    }

    @Override
    public String deleteReading(Long id) throws Exception {
        readingRepository.findById(id)
                .orElseThrow(()-> new Exception("Reading not found"));
        readingRepository.deleteById(id);
        return "Plant deleted";
    }

    @Override
    public DtoReadingsTotals readingsTotals(){
        DtoReadingsTotals dto = new DtoReadingsTotals();
        dto.setReadingsTotals(readingRepository.getTotalReadings());
        System.out.println(dto);
        dto.setAlertsRed(serviceAlert.sumTypeAlert(TypeAlert.RED));
        System.out.println(dto);
        dto.setAlertsMedias(serviceAlert.sumTypeAlert(TypeAlert.MEDIUM));
        System.out.println(dto);
        dto.setAlertsOk(serviceAlert.sumTypeAlert(TypeAlert.OK));
        System.out.println(dto);

        return dto;
    }

}
