package com.br.autopecas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.autopecas.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
