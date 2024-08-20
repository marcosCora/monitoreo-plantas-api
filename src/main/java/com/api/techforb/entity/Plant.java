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
    @ManyToOne
    private Country country;
    @OneToMany(mappedBy = "plant", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "plant")
    private List<Reading> readings;
    private int sensorsDisiable;
}
