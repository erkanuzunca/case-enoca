package com.example.demo.data.repository;

import com.example.demo.data.entity.PriceHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceHistoryRepository extends JpaRepository<PriceHistoryEntity, Long> {

    // Ürün bazında fiyat geçmişini almak için
 //   List<PriceHistoryEntity> findByProductId(Long productId);
}
