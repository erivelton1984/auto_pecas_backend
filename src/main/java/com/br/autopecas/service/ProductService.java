package com.br.autopecas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.br.autopecas.model.Product;
import com.br.autopecas.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public List<Product> list() {
        return repository.findAll();
    }

    public Product save(Product product) {
        return repository.save(product);
    }

}
