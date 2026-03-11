package com.br.autopecas.service;

import java.util.List;

import com.br.autopecas.dto.ProductDTO;
import com.br.autopecas.model.Category;
import com.br.autopecas.model.Product;
import com.br.autopecas.repository.CategoryRepository;
import com.br.autopecas.repository.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository repository,
                          CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    public Product save(ProductDTO dto) {

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));

        Product product = new Product();

        product.setName(dto.getName());
        product.setCode(dto.getCode());
        product.setBrand(dto.getBrand());
        product.setDescription(dto.getDescription());
        product.setCategory(category);

        return repository.save(product);
    }

    public Product update(Long id, ProductDTO dto) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));

        product.setName(dto.getName());
        product.setCode(dto.getCode());
        product.setBrand(dto.getBrand());
        product.setDescription(dto.getDescription());
        product.setCategory(category);

        return repository.save(product);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}