package com.br.autopecas.repository;

import com.br.autopecas.model.VehicleEngine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleEngineRepository extends JpaRepository<VehicleEngine, Long> {

    List<VehicleEngine> findByGenerationId(Long generationId);

}