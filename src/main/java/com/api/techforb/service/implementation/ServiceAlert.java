package com.api.techforb.service.implementation;

import com.api.techforb.entity.Alert;
import com.api.techforb.repository.IRepositoryAlert;
import com.api.techforb.service.IServiceAlert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceAlert implements IServiceAlert {

    @Autowired
    private IRepositoryAlert alertRepository;


    @Override
    public List<Alert> getAllAlert() {
        return alertRepository.findAll();
    }

    @Override
    public List<Alert> getAlertsForType(String type) {
        List<Alert> alerts = alertRepository.findAll();
        List<Alert> alertsType = new ArrayList<>();
        for (Alert alert : alerts){
            if(alert.getTypeAlert().equals(type)){
                alertsType.add(alert);
            }
        }
        return alertsType;
    }

    @Override
    public String deleteAlert(Long id) throws Exception {
        alertRepository.findById(id)
                .orElseThrow(()-> new Exception("Alert not found"));
        alertRepository.deleteById(id);
        return "Alert deleted";
    }
}
