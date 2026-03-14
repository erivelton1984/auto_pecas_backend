package com.br.autopecas.dto;

import lombok.Data;

@Data
public class ProductApplicationDTO {

    private Long productId;
    private Long engineId;

    private Integer yearStart;
    private Integer yearEnd;

    private String position;
    private String side;

    private String notes;

}
