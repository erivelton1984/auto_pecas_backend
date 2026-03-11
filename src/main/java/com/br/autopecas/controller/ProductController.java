package com.br.autopecas.controller;

import java.util.List;

import com.br.autopecas.dto.ProductDTO;
import org.springframework.web.bind.annotation.*;

import com.br.autopecas.model.Product;
import com.br.autopecas.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {

        this.service = service;
    }

    @GetMapping
    public List<Product> getAll() {

        return service.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {

        return service.getById(id);
    }

    @PostMapping
    public Product create(@RequestBody Product product) {

        return service.save(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id,
                             @RequestBody ProductDTO request){
        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

        service.delete(id);
    }

}