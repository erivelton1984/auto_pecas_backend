package com.br.autopecas.controller;

import com.br.autopecas.dto.InventoryDTO;
import com.br.autopecas.model.Inventory;
import com.br.autopecas.service.InventoryService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<InventoryDTO> create(@RequestBody InventoryDTO inventoryDTO) {

        service.save(inventoryDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(inventoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> update(
            @PathVariable Long id,
            @RequestBody InventoryDTO request) {

        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}