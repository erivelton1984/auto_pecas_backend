package com.br.autopecas.controller;

import java.util.List;

import com.br.autopecas.dto.ProductDTO;
import com.br.autopecas.dto.SearchResultDTO;
import com.br.autopecas.model.Product;
import com.br.autopecas.service.InventoryService;
import com.br.autopecas.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.br.autopecas.dto.OfferDTO;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;
    private final InventoryService inventoryService;
    private final ProductService productService;

    public ProductController(ProductService service, InventoryService inventoryService, ProductService productService) {

        this.service = service;
        this.inventoryService = inventoryService;
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/{id}/offers")
    public List<OfferDTO> getOffers(@PathVariable Long id){

        return inventoryService.getOffersByProduct(id);

    }

    @GetMapping("/{id}/nearby")
    public List<OfferDTO> getNearbyOffers(@PathVariable Long id, @RequestParam Double lat, @RequestParam Double lon){

        return inventoryService.getNearbyOffers(id, lat, lon);
    }

    @GetMapping("/search")
    public List<SearchResultDTO> search(@RequestParam String term, @RequestParam Double lat, @RequestParam Double lon){

        return inventoryService.search(term, lat, lon);

    }

    @GetMapping("/smart-search")
    public List<Product> smartSearch(@RequestParam String term){
        return productService.smartSearch(term);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductDTO request) {

        Product product = service.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductDTO request) {

        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}