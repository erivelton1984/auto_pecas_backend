package com.br.autopecas.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OfferDTO {

    private String companyName;
    private String city;
    private Double price;
    private Integer quantity;
    private Double distanceKm;

}
