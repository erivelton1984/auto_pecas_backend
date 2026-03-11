package com.br.autopecas.dto;

import lombok.Data;

@Data
public class SearchResultDTO {

    private String productName;
    private String productCode;
    private String brand;
    private String companyName;
    private String city;
    private Double price;
    private Integer quantity;
    private Double distanceKm;

}
