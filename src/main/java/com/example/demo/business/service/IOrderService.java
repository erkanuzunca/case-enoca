package com.example.demo.business.service;
import com.example.demo.data.entity.OrderEntity;

import java.util.List;

public interface IOrderService {
    OrderEntity placeOrder(Long customerId);
    OrderEntity getOrderForCode(Long orderId);
    List<OrderEntity> getAllOrdersForCustomer(Long customerId);
}