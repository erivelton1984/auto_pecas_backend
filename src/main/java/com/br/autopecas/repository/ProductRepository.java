package com.br.autopecas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.autopecas.model.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCodeContainingIgnoreCase(String code);

    List<Product> findByBrandContainingIgnoreCase(String brand);

    @Query("""
        SELECT DISTINCT p FROM Product p
        LEFT JOIN p.oems o
        LEFT JOIN p.crossReferences cr
        WHERE 
            LOWER(p.code) LIKE LOWER(CONCAT('%', :term, '%'))
            OR LOWER(o.oem) LIKE LOWER(CONCAT('%', :term, '%'))
            OR LOWER(cr.code) LIKE LOWER(CONCAT('%', :term, '%'))
    """)
    List<Product> smartSearch(String term);
}
