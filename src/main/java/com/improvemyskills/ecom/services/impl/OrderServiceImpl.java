package com.improvemyskills.ecom.services.impl;

import com.improvemyskills.ecom.models.Customer;
import com.improvemyskills.ecom.models.Order;
import com.improvemyskills.ecom.models.Product;
import com.improvemyskills.ecom.models.enumerations.OrderStatus;
import com.improvemyskills.ecom.repository.CustomerRepository;
import com.improvemyskills.ecom.repository.OrderRepository;
import com.improvemyskills.ecom.repository.ProductRepository;
import com.improvemyskills.ecom.services.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }


    @Override
    public Order placeAnOrder(Long customerId, List<Long> productsList) {

        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (customerOptional.isEmpty()){
            throw new RuntimeException(
                    String.format("Customer with id : %s not found", customerId));
        }
        List<Product> products =
                productRepository.findAllById(productsList);
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setOrderReference(UUID.randomUUID().toString());
        order.setCustomer(customerOptional.get());
        order.setProducts(products);
        order.setOrderStatus(OrderStatus.CREATED);
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order updateOrderStatus(String orderStatus, Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isEmpty()){
            throw new RuntimeException(
                    String.format("No order with id : %s found ", orderId));
        }
        optionalOrder.get().setOrderStatus(
                OrderStatus.valueOf(orderStatus)
        );
        return orderRepository.save(optionalOrder.get());
    }

    @Override
    public List<Order> getOrderByCustomer(Long customerId) {
        return orderRepository.findByCustomer_Id(customerId);
    }
}
