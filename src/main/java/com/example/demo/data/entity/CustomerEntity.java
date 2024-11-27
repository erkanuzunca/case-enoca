package com.example.demo.data.entity;

import com.example.demo.data.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CustomerEntity extends BaseEntity {

    private String name;
    private String email;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private CartEntity cart; // Müşteriye ait sepet

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<OrderEntity> orders; // Müşteriye ait siparişler
}
