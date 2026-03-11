package com.br.autopecas.service;

import com.br.autopecas.dto.CategoryDTO;
import com.br.autopecas.model.Category;
import com.br.autopecas.repository.CategoryRepository;
import org.springframework.stereotype.Service;


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

    public Category save(Category cat) {

        Category category = new Category();

        category.setName(cat.getName());

        return repository.save(category);
    }

    public Category getById(Long id) {

        return repository.findById(id).orElseThrow();
    }

    public Category update(Long id, CategoryDTO categoryDTO){

        Category cat = repository.findById(id)
                .orElseThrow();

        cat.setName(categoryDTO.getName());

        return repository.save(cat);
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }
}
