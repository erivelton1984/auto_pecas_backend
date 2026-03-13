package com.br.autopecas.dto;

import com.br.autopecas.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CrossReferenceDTO {

    private Long id;
    private String code;
    private String brand;
    private Product product;
}
