package com.improvemyskills.ecom.services;

import com.improvemyskills.ecom.models.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Customer save(Customer customer);
    Optional<Customer> findById(Long customerId);
    List<Customer> getAllCustomers();

}
