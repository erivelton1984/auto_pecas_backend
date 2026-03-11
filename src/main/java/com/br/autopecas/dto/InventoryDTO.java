package com.br.autopecas.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDTO {

    private Long id;

    private Long companyId;

    private String companyName; // opcional, para exibir no front

    private Long productId;

    private String productName; // opcional, para exibir no front

    private Integer quantity;

    private BigDecimal price;
}