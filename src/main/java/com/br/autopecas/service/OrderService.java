package com.br.autopecas.service;

import com.br.autopecas.enums.OrderStatus;
import com.br.autopecas.model.Inventory;
import com.br.autopecas.model.Order;
import com.br.autopecas.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order save(Order order) {
        return repository.save(order);
    }

    public List<Order> getAll() {
        return repository.findAll();
    }

    public Order getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Order confirmPayment(Long orderId) {

        Order order = repository.findById(orderId)
                .orElseThrow();

        order.setStatus(String.valueOf(OrderStatus.PAID));

        // baixar estoque
        order.getItems().forEach(item -> {

            Inventory inventory = item.getInventory();

            inventory.setQuantity(
                    inventory.getQuantity() - item.getQuantity()
            );

        });

        return repository.save(order);
    }
}