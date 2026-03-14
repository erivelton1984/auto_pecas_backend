package com.br.autopecas.repository;

import com.br.autopecas.model.ProductVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVehicleRepository extends JpaRepository<ProductVehicle, Long> {
}
