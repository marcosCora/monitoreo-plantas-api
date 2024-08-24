package com.api.techforb.service.implementation;

import com.api.techforb.Enums.TypeAlert;
import com.api.techforb.Enums.TypeReadings;
import com.api.techforb.dtos.DtoPlant;
import com.api.techforb.dtos.DtoReadingsTotals;
import com.api.techforb.entity.Alert;
import com.api.techforb.entity.Plant;
import com.api.techforb.entity.Reading;
import com.api.techforb.repository.IRepositoryReading;
import com.api.techforb.service.IServiceAlert;
import com.api.techforb.service.IServiceReading;
import lombok.Locked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
      public String deleteReadings(List<Long> ids) throws Exception {return "";}
//        for (Long l : ids){
//            readingRepository.deleteById(l);
//        }
//        return "Reading deleted";
//    }

    @Override
    public DtoReadingsTotals readingsTotals(){
        DtoReadingsTotals dto = new DtoReadingsTotals();
        dto.setReadingsTotals(readingRepository.getTotalReadings());
        dto.setAlertsRed(serviceAlert.sumTypeAlert(TypeAlert.RED));
        dto.setAlertsMedias(serviceAlert.sumTypeAlert(TypeAlert.MEDIUM));
        dto.setAlertsOk(serviceAlert.sumTypeAlert(TypeAlert.OK));

        return dto;
    }

    @Override
    public DtoPlant readingByPlant(DtoPlant dto){
        List<Reading> readings = getAllReading();
        int contReading = 0;
        int contAlertMedium = 0;
        int contAlertRed = 0;
        Long contAlertOk = 0L;
        for (Reading r : readings){
            if(r.getPlant().getIdPlant() == dto.getIdPlant()){
                contReading++;
                if(r.getAlert().getTypeAlert().equals(TypeAlert.MEDIUM)){
                    contAlertMedium++;
                } else if (r.getAlert().getTypeAlert().equals(TypeAlert.RED)) {
                    contAlertRed++;
                }else{
                    contAlertOk++;
                }
            }
        }

        dto.setCantReadings((long) contReading);
        dto.setCantAlertMedium((long) contAlertMedium);
        dto.setCantAlertRed((long) contAlertRed);
        dto.setCantReadingOk(contAlertOk);
        return dto;
    }

    //probablemente exista una mejor manera de modularizar y hacer esto
    @Override
    public void createReadingRandom(Plant p, Long cantReading, Long alertOk, Long alertMedium, Long alertRed){
        List<Reading> readings = new ArrayList<>();
        int contAlertOkAsigned = 0;//cantidad de alertas ok asignadas a las lecturas
        int contAlertMediumAsigned = 0;
        int contAlertRedAsigned = 0;
        for(int c = 0; c<cantReading; c++){
            Alert alert = new Alert();
            Reading reading = new Reading();
            if (contAlertOkAsigned < alertOk){
                alert.setTypeAlert(TypeAlert.OK);
                contAlertOkAsigned++;
            } else if (contAlertMediumAsigned < alertMedium) {
                alert.setTypeAlert(TypeAlert.MEDIUM);
                contAlertMediumAsigned++;
            } else if (contAlertRedAsigned < alertRed) {
                alert.setTypeAlert(TypeAlert.RED);
                contAlertRedAsigned++;
            }
            int valueRandom = new Random().nextInt(200)+1;
            reading.setTypeReading(typeReadingRandom());
            reading.setValue(valueRandom);
            reading.setAlert(alert);
            reading.setPlant(p);
            readings.add(reading);
        }
        //System.out.println(readings.toString());
        readingRepository.deleteReadingsByPlant(p.getIdPlant());
        saveReading(readings);
    }



    @Override
    public TypeReadings typeReadingRandom(){
        TypeReadings[] values = TypeReadings.values();
        int randomIndex = new Random().nextInt(values.length);
        return values[randomIndex];
    }

}
