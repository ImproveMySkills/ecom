package com.improvemyskills.ecom.controllers;

import com.improvemyskills.ecom.models.Customer;
import com.improvemyskills.ecom.services.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/customers")
    ResponseEntity<Customer> saveCustomer(@RequestBody
                                          Customer customer){
        return ResponseEntity.ok(
                customerService.save(customer)
        );
    }

    @GetMapping("/customers/{id}")
    ResponseEntity<Customer> findByCustomerId(@PathVariable Long id){
        return ResponseEntity.ok(
                customerService.findById(id).get()
        );
    }
}
