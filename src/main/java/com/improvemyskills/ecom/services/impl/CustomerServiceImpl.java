package com.improvemyskills.ecom.services.impl;

import com.improvemyskills.ecom.models.Customer;
import com.improvemyskills.ecom.repository.CustomerRepository;
import com.improvemyskills.ecom.services.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
        return customerRepository.findById(customerId);
    }
}
