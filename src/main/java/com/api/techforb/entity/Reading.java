package com.api.techforb.entity;

import com.api.techforb.Enums.TypeReadings;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity(name = "readings")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReading;
    @Enumerated(EnumType.STRING)
    private TypeReadings typeReading;
    @ManyToOne
    @JoinColumn(name = "fk_plant",
                referencedColumnName = "idPlant")
    @JsonIgnoreProperties(value = "readings")
    private Plant plant;
    private double value;
    @OneToMany(mappedBy = "reading", cascade = CascadeType.ALL)
    private List<Alert> alerts;
}
