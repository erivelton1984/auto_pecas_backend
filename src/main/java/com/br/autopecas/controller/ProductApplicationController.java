package com.br.autopecas.controller;

import com.br.autopecas.dto.ProductApplicationDTO;
import com.br.autopecas.model.ProductApplication;
import com.br.autopecas.service.ProductApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-applications")
@RequiredArgsConstructor
public class ProductApplicationController {

    private final ProductApplicationService service;

    @PostMapping
    public ProductApplication save(@RequestBody ProductApplicationDTO dto){
        return service.save(dto);
    }

}
