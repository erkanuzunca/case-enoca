package com.example.demo.business.service.impl;

import com.example.demo.business.service.ICartService;
import com.example.demo.business.service.IOrderService;
import com.example.demo.data.entity.CartEntity;
import com.example.demo.data.entity.OrderEntity;
import com.example.demo.data.entity.OrderItemEntity;
import com.example.demo.data.repository.IOrderRepository;
import com.example.demo.data.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {
    private final IOrderRepository orderRepository;
    private final ICartService cartService;
    private final IProductRepository productRepository;

    public OrderServiceImpl(IOrderRepository orderRepository, ICartService cartService, IProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.productRepository = productRepository;
    }

    @Override
    public OrderEntity placeOrder(Long customerId) {
        // Müşteri sepetini getir
        CartEntity cart = cartService.getCart(customerId);

        if (cart.getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty. Cannot place an order.");
        }

        // Yeni bir sipariş oluştur
        OrderEntity order = new OrderEntity();
        order.setCustomer(cart.getCustomer());
        order.setTotalPrice(cart.getTotalPrice());

        // Sepet öğelerini sipariş öğelerine dönüştür ve stoğu güncelle
        order.setItems(cart.getItems().stream().map(cartItem -> {
            // Sipariş öğesi oluştur
            OrderItemEntity orderItem = new OrderItemEntity();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPriceAtPurchase(cartItem.getProduct().getPrice());

            // Ürünün stoğunu güncelle
            int remainingStock = cartItem.getProduct().getStockQuantity() - cartItem.getQuantity();
            if (remainingStock < 0) {
                throw new IllegalArgumentException("Insufficient stock for product: " + cartItem.getProduct().getId());
            }
            cartItem.getProduct().setStockQuantity(remainingStock);
            productRepository.save(cartItem.getProduct());

            return orderItem;
        }).toList());

        // Sepeti boşalt
        cartService.emptyCart(customerId);

        // Siparişi kaydet ve döndür
        return orderRepository.save(order);
    }

    @Override
    public OrderEntity getOrderForCode(Long orderId) {
        // Belirtilen sipariş koduna göre siparişi getir
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + orderId));
    }

    @Override
    public List<OrderEntity> getAllOrdersForCustomer(Long customerId) {
        // Belirtilen müşteriye ait tüm siparişleri getir
        return orderRepository.findByCustomerId(customerId);
    }
}
