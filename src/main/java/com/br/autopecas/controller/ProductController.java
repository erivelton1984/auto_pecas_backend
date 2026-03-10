package com.br.autopecas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.br.autopecas.service.ProductService;
import com.br.autopecas.model.Product;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductService service;

    @GetMapping
    public List<Product> list() {
        return service.list();
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.save(product);
    }

}
