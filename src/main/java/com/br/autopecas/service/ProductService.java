package com.br.autopecas.service;

import java.util.List;

import com.br.autopecas.repository.ProductRepository;
import org.springframework.stereotype.Service;

import com.br.autopecas.model.Product;


@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public Product getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}