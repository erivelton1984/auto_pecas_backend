package com.br.autopecas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductVehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private VehicleEngine engine;
}