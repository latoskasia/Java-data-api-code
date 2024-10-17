package com.example.data_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class DataController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return ResponseEntity.ok(customer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        if (customer.getEmail() == null || customer.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        }
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.status(201).body(savedCustomer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setName(customerDetails.getName());
            customer.setEmail(customerDetails.getEmail());
            customer.setPassword(customerDetails.getPassword());
            Customer updatedCustomer = customerRepository.save(customer);
            return ResponseEntity.ok(updatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}




//package com.example.data_service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//
//@RestController
//@RequestMapping("api/customers")
//public class DataController {
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @GetMapping
//    public List<Customer> getAllCustomers(){
//        return customerRepository.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Customer>getCustomerById(@PathVariable Long id) {
//        Optional<Customer> customer = customerRepository.findById(id);
//        if (customer.isPresent()){
//            return ResponseEntity.ok(customer.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping
//    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
//        Customer savedCustomer = customerRepository.save(customer);
//        return ResponseEntity.ok(savedCustomer);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Customer>updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails){
//        Optional<Customer> optionalCustomer = customerRepository.findById(id);
//        if(optionalCustomer.isPresent()){
//            Customer customer = optionalCustomer.get();
//            customer.setName(customerDetails.getName());
//        }
//        customer.setId(id);
//        Customer updatedCustomer = customerRepository.save(customer);
//        return ResponseEntity.ok(updatedCustomer);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Customer> deleteCustomer (@PathVariable Long id){
//        customerRepository.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
//
//
//
//}
