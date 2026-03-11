package com.br.autopecas.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private VehicleBrand brand;

    @OneToMany(mappedBy = "model")
    private List<VehicleGeneration> generations;
}