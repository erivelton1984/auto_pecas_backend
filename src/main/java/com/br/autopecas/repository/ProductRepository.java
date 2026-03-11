package com.br.autopecas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.autopecas.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCodeContainingIgnoreCase(String code);

    List<Product> findByBrandContainingIgnoreCase(String brand);
}
