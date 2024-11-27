package com.example.demo.data.entity;

import com.example.demo.data.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity extends BaseEntity {

    // Müşteri ile ilişki (Bir müşteri birden fazla sipariş verebilir)
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonBackReference
    private CustomerEntity customer;

    // Sipariş öğeleri (Bir sipariş birden fazla öğe içerebilir)
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderItemEntity> items;

    // Siparişin toplam fiyatı
    private double totalPrice;

    // Toplam fiyatı hesaplamak için yardımcı bir metot ekleme
    public void calculateTotalPrice() {
        double total = 0.0;
        for (OrderItemEntity item : items) {
            total += item.getPriceAtPurchase() * item.getQuantity();
        }
        this.totalPrice = total;
    }
}
