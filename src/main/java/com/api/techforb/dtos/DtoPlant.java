package com.api.techforb.dtos;

import lombok.Data;

@Data
public class DtoPlant {
    private Long idPlant;
    private String name;
    private String nameCountry;
    private Long cantReadings;
    private Long cantAlertMedium;
    private Long cantAlertRed;
    private int sensorsDisiabled;
}
