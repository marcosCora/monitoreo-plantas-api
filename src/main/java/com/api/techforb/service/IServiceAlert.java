package com.api.techforb.service;

import com.api.techforb.Enums.TypeAlert;
import com.api.techforb.entity.Alert;

import java.util.List;

public interface IServiceAlert {

    public List<Alert> getAllAlert();
    public List<Alert> getAlertsForType(String type);
    public String deleteAlert(Long id)throws Exception;
    public Long sumTypeAlert(TypeAlert typeAlert);

}
