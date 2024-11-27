package com.example.demo.data.repository;

import com.example.demo.data.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartRepository extends JpaRepository<CartEntity, Long> {

    /**
     * Belirtilen ürünün bulunduğu sepetleri getirir.
     *
     * @param productId Ürün ID'si
     * @return Ürünün bulunduğu sepetlerin listesi
     */
    @Query("SELECT c FROM CartEntity c JOIN c.items i WHERE i.product.id = :productId")
    List<CartEntity> findCartsByProductId(@Param("productId") Long productId);
}
