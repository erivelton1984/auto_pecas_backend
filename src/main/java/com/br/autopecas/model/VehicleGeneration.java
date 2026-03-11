package com.br.autopecas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class VehicleGeneration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer yearStart;

    private Integer yearEnd;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private Vehicle model;

    @OneToMany(mappedBy = "generation")
    private List<VehicleEngine> engines;
}