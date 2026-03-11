package com.br.autopecas.service;

import com.br.autopecas.dto.CategoryDTO;
import com.br.autopecas.model.Category;
import com.br.autopecas.repository.CategoryRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
    }

    public Category save(CategoryDTO dto) {

        Category category = new Category();
        category.setName(dto.getName());

        return repository.save(category);
    }

    public Category update(Long id, CategoryDTO dto) {

        Category category = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));

        category.setName(dto.getName());

        return repository.save(category);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}