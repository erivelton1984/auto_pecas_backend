package com.br.autopecas.service;

import com.br.autopecas.dto.ProductApplicationDTO;
import com.br.autopecas.model.Product;
import com.br.autopecas.model.ProductApplication;
import com.br.autopecas.model.VehicleEngine;
import com.br.autopecas.repository.ProductApplicationRepository;
import com.br.autopecas.repository.ProductRepository;
import com.br.autopecas.repository.VehicleEngineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductApplicationService {

    private final ProductApplicationRepository repository;
    private final ProductRepository productRepository;
    private final VehicleEngineRepository engineRepository;

    public ProductApplication save(ProductApplicationDTO dto){

        Product product = productRepository.findById(dto.getProductId()).orElseThrow();
        VehicleEngine engine = engineRepository.findById(dto.getEngineId()).orElseThrow();

        ProductApplication pa = new ProductApplication();

        pa.setProduct(product);
        pa.setEngine(engine);
        pa.setYearStart(dto.getYearStart());
        pa.setYearEnd(dto.getYearEnd());
        pa.setPosition(dto.getPosition());
        pa.setSide(dto.getSide());
        pa.setNotes(dto.getNotes());

        return repository.save(pa);
    }

}
