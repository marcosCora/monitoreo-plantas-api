package com.api.techforb.dtos;

import lombok.Data;

@Data
public class DtoReadingsTotals {
    private Long readingsTotals;
    private Long alertsOk;
    private Long alertsRed;
    private Long AlertsMedias;
    private Long sensorsDisiabled;
}
