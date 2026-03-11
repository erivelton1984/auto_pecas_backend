package com.br.autopecas.service;

import com.br.autopecas.dto.CompanyDTO;
import com.br.autopecas.dto.InventoryDTO;
import com.br.autopecas.model.Company;
import com.br.autopecas.model.Inventory;
import com.br.autopecas.repository.CompanyRepository;
import com.br.autopecas.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {

        this.repository = repository;
    }

    public List<Inventory> getAll() {

        return repository.findAll();
    }

    public Company save(Inventory inv) {

        Inventory inventory = new Inventory();

        inventory.setProduct(inv.getProduct());
        inventory.setPrice(inv.getPrice());
        inventory.setCompany(inv.getCompany().getId());
        inventory.setQuantity(inv.getQuantity());

        return repository.save(inventory);
    }

    public Inventory getById(Long id) {

        return repository.findById(id).orElseThrow();
    }

    public Inventory update(Long id, InventoryDTO inventoryDTO){

        Inventory inv = repository.findById(id)
                .orElseThrow();

        inv.setProduct(inventoryDTO.getProduct());
        inv.setPrice(inventoryDTO.getPrice());
        inv.setCompany(inventoryDTO.getCompany());
        inv.setQuantity(inventoryDTO.getQuantity());

        return repository.save(inv);
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }
}
