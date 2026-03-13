package com.br.autopecas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class VehicleEngine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String engine;

    private String fuel;

    @ManyToOne
    @JoinColumn(name = "generation_id")
    @JsonBackReference
    private VehicleGeneration generation;
}