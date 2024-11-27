package com.example.demo.controller;

import com.example.demo.business.service.ICustomerService;
import com.example.demo.data.entity.CustomerEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    // JSON ile gelen veriyi alıyoruz
    @PostMapping
    public ResponseEntity<String> addCustomer(@RequestBody CustomerEntity customerEntity) {
        customerService.addCustomer(customerEntity);
        return ResponseEntity.ok("Customer added successfully");
    }



}
