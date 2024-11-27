package com.example.demo.business.service.impl;

import com.example.demo.business.service.ICartService;
import com.example.demo.data.entity.CartEntity;
import com.example.demo.data.entity.CartItemEntity;
import com.example.demo.data.entity.CustomerEntity;
import com.example.demo.data.entity.ProductEntity;
import com.example.demo.data.repository.ICartRepository;
import com.example.demo.data.repository.ICustomerRepository;
import com.example.demo.data.repository.IProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements ICartService {
    private final ICartRepository cartRepository;
    private final IProductRepository productRepository;
    private final ICustomerRepository customerRepository;

    public CartServiceImpl(ICartRepository cartRepository, IProductRepository productRepository, ICustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public CartEntity getCart(Long customerId) {
        CustomerEntity customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + customerId));

        // Eğer müşteri için sepet yoksa bir sepet oluşturuluyor
        if (customer.getCart() == null) {
            CartEntity newCart = new CartEntity();
            newCart.setCustomer(customer);
            newCart.setTotalPrice(0.0);
            customer.setCart(newCart);
            customerRepository.save(customer); // Yeni sepetle müşteri kaydediliyor
        }

        return customer.getCart(); // Sepet geri dönüyor
    }

    @Override
    public CartEntity updateCart(Long customerId) {
        CartEntity cart = getCart(customerId);

        // Sepet öğelerinin fiyatını topluyor
        double totalPrice = cart.getItems().stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        cart.setTotalPrice(totalPrice);

        return cartRepository.save(cart); // Sepet güncelleniyor
    }

    @Override
    public void emptyCart(Long customerId) {
        CartEntity cart = getCart(customerId);
        cart.getItems().clear(); // Sepet boşaltılıyor
        cart.setTotalPrice(0.0); // Toplam fiyat sıfırlanıyor
        cartRepository.save(cart); // Sepet kaydediliyor
    }

    @Override
    public void addProductToCart(Long customerId, Long productId, int quantity) {
        CartEntity cart = getCart(customerId);
        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + productId));

        if (product.getStockQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient stock for product: " + productId);
        }

        // Ürün zaten sepette varsa, miktarını artırıyoruz
        Optional<CartItemEntity> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItemEntity cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            // Yeni ürün ekleniyor
            CartItemEntity cartItem = new CartItemEntity();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cart.getItems().add(cartItem);
        }

        // Ürünün stok miktarını güncelle
        product.setStockQuantity(product.getStockQuantity() - quantity);
        productRepository.save(product);

        updateCart(customerId); // Sepeti güncelle
    }

    @Override
    public void removeProductFromCart(Long customerId, Long productId) {
        // Sepeti alıyoruz
        CartEntity cart = getCart(customerId);
        if (cart == null) {
            throw new RuntimeException("Cart not found for customer ID: " + customerId);
        }

        System.out.println("Customer ID: " + customerId);
        System.out.println("Cart Items: " + cart.getItems());

        // Ürünün sepette olup olmadığını kontrol ediyoruz
        Optional<CartItemEntity> existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItemEntity cartItem = existingItem.get();
            cart.getItems().remove(cartItem);

            // Ürünün stok miktarını geri yüklüyoruz
            ProductEntity product = cartItem.getProduct();
            product.setStockQuantity(product.getStockQuantity() + cartItem.getQuantity());
            productRepository.save(product);

            // Sepetin toplam fiyatını güncelliyoruz
            double newTotalPrice = cart.getItems().stream()
                    .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                    .sum();
            cart.setTotalPrice(newTotalPrice);

            cartRepository.save(cart); // Sepeti kaydediyoruz
        } else {
            throw new IllegalArgumentException("Product not found in cart for product id: " + productId);
        }
    }

    }



