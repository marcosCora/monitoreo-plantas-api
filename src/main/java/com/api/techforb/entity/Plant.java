package com.api.techforb.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "plants")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Plant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlant;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Country country;

    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_readings",
            referencedColumnName = "idReadings")
    private List<Reading> readings;
    private int sensorsDisiable;
}
