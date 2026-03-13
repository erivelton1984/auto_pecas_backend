package com.br.autopecas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductVehicleResponse {

    private String productName;
    private String productCode;
    private String brand;
    private String companyName;
    private String city;
    private Double price;
    private Integer quantity;
    private Double distanceKm;
}