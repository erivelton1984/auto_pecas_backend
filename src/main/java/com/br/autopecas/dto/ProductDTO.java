package com.br.autopecas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;

    private String name;

    private String code; // código da peça

    private String brand;

    private String description;

    private Long categoryId; // referenciando a categoria pelo ID

    private String categoryName; // opcional, para exibir junto
}