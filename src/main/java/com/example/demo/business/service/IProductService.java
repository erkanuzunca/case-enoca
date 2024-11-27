package com.example.demo.business.service;
import com.example.demo.data.entity.ProductEntity;

import java.util.List;

public interface IProductService {
    ProductEntity getProduct(Long id);
    ProductEntity createProduct(ProductEntity product);
    ProductEntity updateProduct(Long id, ProductEntity updatedProduct);
    void deleteProduct(Long id);
}