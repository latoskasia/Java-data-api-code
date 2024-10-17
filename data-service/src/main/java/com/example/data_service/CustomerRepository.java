package com.example.data_service;

import com.example.data_service.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
//    Customer findByEmail(String email);
    Optional<Customer> findByEmail(String email);
}