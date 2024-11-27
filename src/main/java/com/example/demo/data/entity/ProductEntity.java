package com.example.demo.data.entity;

import com.example.demo.data.BaseEntity;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductEntity extends BaseEntity {
    private String name;
    private double price;
    private int stockQuantity;
}
