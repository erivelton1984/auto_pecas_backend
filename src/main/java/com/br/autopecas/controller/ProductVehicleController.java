package com.br.autopecas.controller;

import com.br.autopecas.dto.ProductVehicleDTO;
import com.br.autopecas.model.ProductVehicle;
import com.br.autopecas.service.ProductVehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-vehicles")
@RequiredArgsConstructor
public class ProductVehicleController {

    private final ProductVehicleService service;

    @PostMapping
    public ProductVehicle save(@RequestBody ProductVehicleDTO dto){
        return service.save(dto);
    }

}
