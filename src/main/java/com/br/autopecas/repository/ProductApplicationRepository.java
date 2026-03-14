package com.br.autopecas.repository;

import com.br.autopecas.model.ProductApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductApplicationRepository extends JpaRepository<ProductApplication, Long> {

    List<ProductApplication> findByEngineId(Long engineId);

}
