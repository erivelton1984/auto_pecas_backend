package com.br.autopecas.service;

import com.br.autopecas.dto.InventoryDTO;
import com.br.autopecas.dto.OfferDTO;
import com.br.autopecas.model.Company;
import com.br.autopecas.model.Inventory;
import com.br.autopecas.model.Product;
import com.br.autopecas.repository.CompanyRepository;
import com.br.autopecas.repository.InventoryRepository;
import com.br.autopecas.repository.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository repository;
    private final ProductRepository productRepository;
    private final CompanyRepository companyRepository;

    public InventoryService(
            InventoryRepository repository,
            ProductRepository productRepository,
            CompanyRepository companyRepository) {

        this.repository = repository;
        this.productRepository = productRepository;
        this.companyRepository = companyRepository;
    }

    public List<Inventory> getAll() {
        return repository.findAll();
    }

    public Inventory getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque não encontrado"));
    }

    public Inventory save(InventoryDTO dto) {

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));

        Inventory inventory = new Inventory();

        inventory.setProduct(product);
        inventory.setCompany(company);
        inventory.setPrice(dto.getPrice());
        inventory.setQuantity(dto.getQuantity());

        return repository.save(inventory);
    }

    public List<OfferDTO> getOffersByProduct(Long productId){

        List<Inventory> inventories = repository.findByProductId(productId);

        return inventories.stream().map(inv -> {

            OfferDTO offer = new OfferDTO();

            offer.setCompanyName(inv.getCompany().getName());
            offer.setCity(inv.getCompany().getCity());
            offer.setPrice(inv.getPrice());
            offer.setQuantity(inv.getQuantity());

            return offer;

        }).toList();

    }

    public Inventory update(Long id, InventoryDTO dto) {

        Inventory inventory = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Estoque não encontrado"));

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        Company company = companyRepository.findById(dto.getCompanyId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa não encontrada"));

        inventory.setProduct(product);
        inventory.setCompany(company);
        inventory.setPrice(dto.getPrice());
        inventory.setQuantity(dto.getQuantity());

        return repository.save(inventory);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}