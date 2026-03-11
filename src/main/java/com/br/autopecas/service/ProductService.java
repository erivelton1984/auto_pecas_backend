package com.br.autopecas.service;

import java.util.List;

import com.br.autopecas.dto.ProductDTO;
import com.br.autopecas.model.Category;
import com.br.autopecas.repository.ProductRepository;
import org.springframework.stereotype.Service;

import com.br.autopecas.model.Product;


@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll() {

        return repository.findAll();
    }

    public Product save(Product product) {

        Product produce = new Product();

        produce.setName(product.getName());
        produce.setCode(product.getCode());
        produce.setCategory(product.getCategory());

        return repository.save(product);
    }

    public Product getById(Long id) {

        return repository.findById(id).orElseThrow();
    }

    public Product update(Long id, ProductDTO produce){

        Product prod = repository.findById(id)
                .orElseThrow();

        prod.setName(produce.getName());
        prod.setCode(produce.getCode());
        prod.setCategory(produce.getCategory());

        return repository.save(prod);
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

}