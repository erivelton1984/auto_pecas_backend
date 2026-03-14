package com.br.autopecas.service;

import com.br.autopecas.dto.ProductVehicleDTO;
import com.br.autopecas.model.Product;
import com.br.autopecas.model.ProductVehicle;
import com.br.autopecas.model.VehicleEngine;
import com.br.autopecas.repository.ProductRepository;
import com.br.autopecas.repository.ProductVehicleRepository;
import com.br.autopecas.repository.VehicleEngineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductVehicleService {

    private final ProductVehicleRepository repository;
    private final ProductRepository productRepository;
    private final VehicleEngineRepository engineRepository;

    public ProductVehicle save(ProductVehicleDTO dto){

        Product product = productRepository.findById(dto.getProductId()).orElseThrow();
        VehicleEngine engine = engineRepository.findById(dto.getEngineId()).orElseThrow();

        ProductVehicle pv = new ProductVehicle();
        pv.setProduct(product);
        pv.setEngine(engine);

        return repository.save(pv);
    }
}
