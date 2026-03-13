package com.br.autopecas.service;

import com.br.autopecas.dto.ProductDTO;
import com.br.autopecas.model.*;
import com.br.autopecas.repository.VehicleBrandRepository;
import com.br.autopecas.repository.VehicleGenerationRepository;
import com.br.autopecas.repository.VehicleRepository;
import jakarta.transaction.UserTransaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleGenerationRepository generationRepository;

    public VehicleService(VehicleRepository vehicleRepository, VehicleGenerationRepository generationRepository) {
        this.vehicleRepository = vehicleRepository;
        this.generationRepository = generationRepository;
    }

    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public VehicleGeneration saveGeneration(VehicleGeneration  vehicleGeneration) {

        VehicleGeneration generation = new VehicleGeneration();

        generation.setYearStart(vehicleGeneration.getYearStart());
        generation.setYearEnd(vehicleGeneration.getYearEnd());

        Vehicle vehicle = vehicleRepository.findById(vehicleGeneration.getModel().getId())
                .orElseThrow(() -> new RuntimeException("Modelo não encontrado"));

        generation.setModel(vehicle);


        return generationRepository.save(generation);
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }
}