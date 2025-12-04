package com.improvemyskills.ecom.services;

import com.improvemyskills.ecom.models.Order;
import com.improvemyskills.ecom.models.enumerations.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order placeAnOrder(Long customerId, List<Long> productsList);
    Optional<Order> getOrder(Long orderId);
    Order update(Order order);
    List<Order> getAllOrders();
    Order updateOrderStatus(String orderStatus, Long orderId);
    List<Order> getOrderByCustomer(Long customerId);
}
