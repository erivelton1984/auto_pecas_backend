package com.br.autopecas.service;

import com.br.autopecas.model.VehicleBrand;
import com.br.autopecas.repository.VehicleBrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleImportService {

    private final VehicleBrandRepository brandRepository;

    public VehicleImportService(VehicleBrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public void importBrands(List<String[]> rows) {

        for (String[] row : rows) {

            VehicleBrand brand = new VehicleBrand();
            brand.setName(row[1]);

            brandRepository.save(brand);
        }
    }
}