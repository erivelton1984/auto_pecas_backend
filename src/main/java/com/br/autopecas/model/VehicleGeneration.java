package com.br.autopecas.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private List<VehicleEngine> engines;
}