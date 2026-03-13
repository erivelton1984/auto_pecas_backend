package com.br.autopecas.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ProductCrossReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Código equivalente
    @Column(nullable = false)
    private String code;

    // Marca equivalente
    private String brand;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference("product-cross")
    private Product product;
}