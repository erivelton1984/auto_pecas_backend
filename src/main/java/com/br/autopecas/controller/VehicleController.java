package com.br.autopecas.controller;

import com.br.autopecas.model.*;
import com.br.autopecas.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleBrandRepository brandRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleGenerationRepository generationRepository;
    private final VehicleEngineRepository engineRepository;

    public VehicleController(
            VehicleBrandRepository brandRepository,
            VehicleRepository vehicleRepository,
            VehicleGenerationRepository generationRepository,
            VehicleEngineRepository engineRepository
    ) {
        this.brandRepository = brandRepository;
        this.vehicleRepository = vehicleRepository;
        this.generationRepository = generationRepository;
        this.engineRepository = engineRepository;
    }

    // listar marcas
    @GetMapping("/brands")
    public List<VehicleBrand> getBrands() {
        return brandRepository.findAll();
    }

    // listar modelos da marca
    @GetMapping("/models/{brandId}")
    public List<Vehicle> getModels(@PathVariable Long brandId) {
        return vehicleRepository.findByBrandId(brandId);
    }

    // listar gerações
    @GetMapping("/generations/{modelId}")
    public List<VehicleGeneration> getGenerations(@PathVariable Long modelId) {
        return generationRepository.findByModelId(modelId);
    }

    // listar motores
    @GetMapping("/engines/{generationId}")
    public List<VehicleEngine> getEngines(@PathVariable Long generationId) {
        return engineRepository.findByGenerationId(generationId);
    }

}