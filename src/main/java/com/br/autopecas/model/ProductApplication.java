package com.br.autopecas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "engine_id")
    private VehicleEngine engine;

    private Integer yearStart;
    private Integer yearEnd;

    private String position;
    // FRONT / REAR

    private String side;
    // LEFT / RIGHT / BOTH

    private String notes;
}
