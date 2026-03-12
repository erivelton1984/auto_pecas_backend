package com.br.autopecas.service;

import java.util.List;

import com.br.autopecas.dto.ProductDTO;
import com.br.autopecas.dto.ProductVehicleResponse;
import com.br.autopecas.model.*;
import com.br.autopecas.repository.CategoryRepository;
import com.br.autopecas.repository.InventoryRepository;
import com.br.autopecas.repository.ProductRepository;

import com.br.autopecas.util.DistanceCalculator;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.Comparator;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final InventoryRepository inventoryRepository;
    private final DistanceCalculator distance;

    public ProductService(ProductRepository repository,
                          CategoryRepository categoryRepository, InventoryRepository inventoryRepository, DistanceCalculator distanceCalculator) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.inventoryRepository = inventoryRepository;
        this.distance = distanceCalculator;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    public List<Product> search(String term) {

        List<Product> productsByName = repository.findByNameContainingIgnoreCase(term);

        if (!productsByName.isEmpty()) {
            return productsByName;
        }

        List<Product> productsByCode = repository.findByCodeContainingIgnoreCase(term);

        if (!productsByCode.isEmpty()) {
            return productsByCode;
        }

        return repository.findByBrandContainingIgnoreCase(term);
    }

    public List<Product> searchProducts(String term) {

        List<Product> products = repository.findByNameContainingIgnoreCase(term);

        if(products.isEmpty()){
            products = repository.findByCodeContainingIgnoreCase(term);
        }

        if(products.isEmpty()){
            products = repository.findByBrandContainingIgnoreCase(term);
        }

        return products;
    }

    public List<ProductVehicleResponse> findByVehicle(Long engineId, Double lat, Double lon) {

        List<Inventory> inventories = inventoryRepository.findByVehicleEngine(engineId);

        return inventories.stream().map(i -> {

                    Company company = i.getCompany();

                    double distance = DistanceCalculator.calculate(
                            lat,
                            lon,
                            company.getLatitude(),
                            company.getLongitude()
                    );

                    return new ProductVehicleResponse(
                            i.getProduct().getName(),
                            i.getProduct().getCode(),
                            i.getProduct().getBrand(),
                            company.getName(),
                            company.getCity(),
                            i.getPrice(),
                            i.getQuantity(),
                            distance
                    );

                }).sorted(Comparator.comparing(ProductVehicleResponse::getDistanceKm))
                .toList();
    }

    public Product save(ProductDTO dto) {

        Product product = new Product();

        product.setName(dto.getName());
        product.setCode(dto.getCode());
        product.setBrand(dto.getBrand());
        product.setDescription(dto.getDescription());

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        product.setCategory(category);

        // Converter OEMs
        if (dto.getOems() != null) {

            List<ProductOEM> oemList = dto.getOems()
                    .stream()
                    .map(o -> {
                        ProductOEM po = new ProductOEM();
                        po.setOem(o);
                        po.setProduct(product);
                        return po;
                    })
                    .toList();

            product.setOems(oemList);
        }

        return repository.save(product);
    }

    public Product update(Long id, ProductDTO dto) {

        Product product = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));

        product.setName(dto.getName());
        product.setCode(dto.getCode());
        product.setBrand(dto.getBrand());
        product.setDescription(dto.getDescription());
        product.setCategory(category);

        return repository.save(product);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}