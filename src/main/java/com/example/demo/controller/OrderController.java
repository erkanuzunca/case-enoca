package com.example.demo.controller;
import com.example.demo.business.service.IOrderService;
import com.example.demo.data.entity.OrderEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{customerId}/place")
    public ResponseEntity<OrderEntity> placeOrder(@PathVariable Long customerId) {
        OrderEntity order = orderService.placeOrder(customerId);
        return ResponseEntity.ok(order);
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderEntity> getOrderForCode(@PathVariable Long orderId) {
        return ResponseEntity.ok(orderService.getOrderForCode(orderId));
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<OrderEntity>> getAllOrdersForCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(orderService.getAllOrdersForCustomer(customerId));
    }
}

