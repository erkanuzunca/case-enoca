package com.example.demo.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Hangi siparişe ait olduğunu belirtme
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private OrderEntity order;

    // Ürün ile ilişki (Bir sipariş öğesi bir ürünü temsil eder)
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    // Ürünün miktarı
    private int quantity;

    // Ürünün satın alındığı anki fiyatı
    private double priceAtPurchase;
}
