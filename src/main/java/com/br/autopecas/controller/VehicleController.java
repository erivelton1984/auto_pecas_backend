package com.br.autopecas.controller;

import com.br.autopecas.model.*;
import com.br.autopecas.repository.*;
import com.br.autopecas.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleBrandRepository brandRepository;
    private final VehicleRepository vehicleRepository;
    private final VehicleGenerationRepository generationRepository;
    private final VehicleEngineRepository engineRepository;

    private final VehicleService vehicleService;

    public VehicleController(
            VehicleBrandRepository brandRepository,
            VehicleRepository vehicleRepository,
            VehicleGenerationRepository generationRepository,
            VehicleEngineRepository engineRepository, VehicleService vehicleService
    ) {
        this.brandRepository = brandRepository;
        this.vehicleRepository = vehicleRepository;
        this.generationRepository = generationRepository;
        this.engineRepository = engineRepository;
        this.vehicleService = vehicleService;
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

    @PostMapping
    public Vehicle save(@RequestBody Vehicle vehicle) {
        return vehicleService.save(vehicle);
    }

    @PostMapping ("/vehicle-brands")
    public VehicleBrand save(@RequestBody VehicleBrand vehicleBrand) {
        return brandRepository.save(vehicleBrand);
    }

    @PostMapping ("/vehicle-generation")
    public ResponseEntity<VehicleGeneration> save(@RequestBody VehicleGeneration vehicleGeneration) {

        VehicleGeneration vehicle = vehicleService.saveGeneration(vehicleGeneration);

        return ResponseEntity.status(HttpStatus.CREATED).body(vehicle);
    }

    @PostMapping ("/vehicle-engines")
    public VehicleEngine save(@RequestBody VehicleEngine vehicleEngine) {
        return engineRepository.save(vehicleEngine);
    }

    @GetMapping
    public List<Vehicle> findAll() {
        return vehicleService.findAll();
    }

}