package com.example.demo;

import com.example.demo.data.entity.*;
import com.example.demo.data.repository.ICartRepository;
import com.example.demo.data.repository.ICustomerRepository;
import com.example.demo.data.repository.IOrderRepository;
import com.example.demo.data.repository.IProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ICustomerRepository customerRepository;
    private final IProductRepository productRepository;
    private final ICartRepository cartRepository;
    private final IOrderRepository orderRepository;

    public DataLoader(ICustomerRepository customerRepository, IProductRepository productRepository, ICartRepository cartRepository, IOrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Ürün ekleyelim
        ProductEntity product1 = new ProductEntity();
        product1.setName("Laptop");
        product1.setPrice(1200.00);
        product1.setStockQuantity(10);
        productRepository.save(product1);

        ProductEntity product2 = new ProductEntity();
        product2.setName("Smartphone");
        product2.setPrice(800.00);
        product2.setStockQuantity(15);
        productRepository.save(product2);

        // Müşteri ekleme
        CustomerEntity customer = new CustomerEntity();
        customer.setName("John Doe");
        customer.setEmail("john.doe@example.com");
        customerRepository.save(customer);

        // Sepet ekleme
        CartEntity cart = new CartEntity();
        cart.setCustomer(customer);
        cart.setTotalPrice(0.0);
        customer.setCart(cart);
        cartRepository.save(cart);

        // Sipariş ekleme
        OrderEntity order = new OrderEntity();
        order.setCustomer(customer);  // Burada müşteri ilişkisini kurduk
        order.setTotalPrice(0.0);  // Başlangıçta sıfır

        orderRepository.save(order);

        // Sipariş öğeleri (OrderItem) ekleme
        OrderItemEntity orderItem1 = new OrderItemEntity();
        orderItem1.setProduct(product1);
        orderItem1.setQuantity(2);
        orderItem1.setPriceAtPurchase(product1.getPrice());

        OrderItemEntity orderItem2 = new OrderItemEntity();
        orderItem2.setProduct(product2);
        orderItem2.setQuantity(1);
        orderItem2.setPriceAtPurchase(product2.getPrice());


        // Siparişin toplam fiyatını hesaplama
        order.setTotalPrice(
                orderItem1.getPriceAtPurchase() * orderItem1.getQuantity() +
                        orderItem2.getPriceAtPurchase() * orderItem2.getQuantity()
        );

        // Sipariş öğelerini kaydetme
        orderRepository.save(order);

        // Sepeti güncelleme
        cart.setTotalPrice(order.getTotalPrice());  // Sepet toplam fiyatını siparişin toplam fiyatı ile güncelle
        cartRepository.save(cart);
        System.out.println("Cart Items: " + cart.getItems());

        CartItemEntity cartItem = new CartItemEntity();
        cartItem.setCart(cart); // İlgili sepete ekleniyor
        cartItem.setProduct(product1); // İlgili ürünle ilişkilendiriliyor
        cartItem.setQuantity(2);

        cart.setItems(List.of(cartItem)); // Sepete ürün ekleniyor
        cart.setTotalPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());

        cartRepository.save(cart);


    }
}
