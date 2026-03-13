package com.br.autopecas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {

    private Long id;
    private String name;
    private String code;
    private String brand;
    private String description;
    private Long categoryId;
    private List<String> oems;
    private List<CrossReferenceDTO> crossReferences;


}