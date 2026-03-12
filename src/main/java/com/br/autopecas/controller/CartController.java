package com.br.autopecas.controller;

import com.br.autopecas.model.Cart;
import com.br.autopecas.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public Cart addItem(
            @RequestParam Long userId,
            @RequestParam Long productId,
            @RequestParam Long companyId,
            @RequestParam Integer quantity
    ) {

        return cartService.addItem(userId, productId, companyId, quantity);

    }

}