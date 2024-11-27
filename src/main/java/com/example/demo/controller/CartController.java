package com.example.demo.controller;
import com.example.demo.business.service.ICartService;
import com.example.demo.data.entity.CartEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    private final ICartService cartService;

    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CartEntity> getCart(@PathVariable Long customerId) {
        return ResponseEntity.ok(cartService.getCart(customerId));
    }

    @PutMapping("/{customerId}/update")
    public ResponseEntity<CartEntity> updateCart(@PathVariable Long customerId) {
        return ResponseEntity.ok(cartService.updateCart(customerId));
    }

    @DeleteMapping("/{customerId}/empty")
    public ResponseEntity<String> emptyCart(@PathVariable Long customerId) {
        cartService.emptyCart(customerId);
        return ResponseEntity.ok("Cart emptied successfully");
    }

    @PostMapping("/{customerId}/products/{productId}/add")
    public ResponseEntity<String> addProductToCart(
            @PathVariable Long customerId,
            @PathVariable Long productId,
            @RequestParam int quantity) {
        cartService.addProductToCart(customerId, productId, quantity);
        return ResponseEntity.ok("Product added to cart");
    }


    @DeleteMapping("/{customerId}/remove")
    public ResponseEntity<String> removeProductFromCart(
            @PathVariable Long customerId,
            @RequestParam Long productId) {
        try {
            cartService.removeProductFromCart(customerId, productId);
            return ResponseEntity.ok("Product removed from cart");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }


}

