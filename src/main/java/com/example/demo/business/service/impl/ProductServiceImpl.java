package com.example.demo.business.service.impl;

import com.example.demo.business.service.IProductService;
import com.example.demo.data.entity.CartEntity;
import com.example.demo.data.entity.CartItemEntity;
import com.example.demo.data.entity.ProductEntity;
import com.example.demo.data.repository.ICartRepository;
import com.example.demo.data.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;
    private final ICartRepository cartRepository; // Sepetler için gerekli repository

    public ProductServiceImpl(IProductRepository productRepository, ICartRepository cartRepository) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
    }




    @Override
    public ProductEntity getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

    @Override
    public ProductEntity createProduct(ProductEntity product) {
        return productRepository.save(product);
    }

    @Override
    public ProductEntity updateProduct(Long id, ProductEntity updatedProduct) {
        ProductEntity product = getProduct(id);
        boolean priceChanged = !Objects.equals(product.getPrice(), updatedProduct.getPrice());

        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setStockQuantity(updatedProduct.getStockQuantity());

        ProductEntity savedProduct = productRepository.save(product);

        // Eğer fiyat değiştiyse, sepetleri güncelle
        if (priceChanged) {
            updateCartsWithNewPrice(savedProduct);
        }

        return savedProduct;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    /**
     * Ürün fiyatı değiştiğinde sepetlerdeki toplam fiyatı günceller.
     *
     * @param updatedProduct Güncellenmiş ürün
     */
    private void updateCartsWithNewPrice(ProductEntity updatedProduct) {
        List<CartEntity> carts = cartRepository.findAll(); // Tüm sepetleri al

        for (CartEntity cart : carts) {
            boolean productInCart = false; // Ürün sepette var mı?
            double newTotalPrice = 0.0; // Yeni toplam fiyat

            for (CartItemEntity item : cart.getItems()) {
                if (item.getProduct().getId().equals(updatedProduct.getId())) {
                    productInCart = true;
                    item.setPriceAtPurchase(updatedProduct.getPrice()); // Yeni fiyatı ayarla
                }
                // Her bir ürün için toplam fiyat hesapla
                newTotalPrice += item.getQuantity() * item.getPriceAtPurchase();
            }

            // Eğer ürün sepette varsa toplam fiyatı güncelle
            if (productInCart) {
                cart.setTotalPrice(newTotalPrice);
                cartRepository.save(cart); // Sepeti kaydet
            }

        }

    }
}