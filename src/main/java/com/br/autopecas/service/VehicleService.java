package com.br.autopecas.service;

import com.br.autopecas.model.Vehicle;
import com.br.autopecas.model.VehicleBrand;
import com.br.autopecas.repository.VehicleBrandRepository;
import com.br.autopecas.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;


    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }
}