package com.example.demo.business.service;

import com.example.demo.data.entity.CartEntity;

public interface ICartService {
    CartEntity getCart(Long customerId);
    CartEntity updateCart(Long customerId);
    void emptyCart(Long customerId);
    void addProductToCart(Long customerId, Long productId, int quantity);
    void removeProductFromCart(Long customerId, Long productId);
}
