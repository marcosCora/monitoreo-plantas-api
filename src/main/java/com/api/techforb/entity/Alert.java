package com.api.techforb.entity;

import com.api.techforb.Enums.TypeAlert;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "alerts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlert;
    @Enumerated(EnumType.STRING)
    private TypeAlert typeAlert;
    @ManyToOne
    @JoinColumn(name = "fk_alert",
            referencedColumnName = "idReading")
    private Reading reading;

}
