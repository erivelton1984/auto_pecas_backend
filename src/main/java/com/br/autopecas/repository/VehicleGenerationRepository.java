package com.br.autopecas.repository;

import com.br.autopecas.model.VehicleGeneration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleGenerationRepository extends JpaRepository<VehicleGeneration, Long> {

    List<VehicleGeneration> findByModelId(Long modelId);

}