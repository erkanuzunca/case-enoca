package com.example.demo.business.service.impl;

import com.example.demo.business.service.ICustomerService;
import com.example.demo.data.entity.CartEntity;
import com.example.demo.data.entity.CustomerEntity;
import com.example.demo.data.repository.ICustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements ICustomerService {
    private final ICustomerRepository customerRepository;

    public CustomerServiceImpl(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void addCustomer(CustomerEntity customerEntity) {
        customerRepository.save(customerEntity);
    }

   // @Override// buraya bak
    public void addCustomer(String name, String email) {
        CustomerEntity customer = new CustomerEntity();
        customer.setName(name);
        customer.setEmail(email);

        // Yeni bir sepet oluşturuluyor
        CartEntity cart = new CartEntity();
        cart.setCustomer(customer);
        cart.setTotalPrice(0.0); // Başlangıçta sepetin toplam fiyatı sıfır

        customer.setCart(cart); // Müşteri ile sepet ilişkilendiriliyor
        customerRepository.save(customer); // Müşteri kaydediliyor
    }
}
