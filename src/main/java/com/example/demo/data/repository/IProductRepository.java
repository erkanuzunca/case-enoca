package com.example.demo.data.repository;
import com.example.demo.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.data.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
    // Bu sınıf ProductEntity'yi veritabanından çekmek için kullanılır.
}
