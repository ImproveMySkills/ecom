package com.improvemyskills.ecom.services.impl;

import com.improvemyskills.ecom.models.Customer;
import com.improvemyskills.ecom.repository.CustomerRepository;
import com.improvemyskills.ecom.services.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        // TODO
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (!customer.isPresent()){
            throw new RuntimeException(String.format("No customer found with ID %s", customerId));
        }
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
