package com.improvemyskills.ecom.controllers;

import com.improvemyskills.ecom.dto.OrderDto;
import com.improvemyskills.ecom.models.Order;
import com.improvemyskills.ecom.services.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/orders/placeanorder")
    ResponseEntity<Order> placeAnOrder(@RequestBody OrderDto orderDto){
        return ResponseEntity.ok(
                orderService.placeAnOrder(orderDto.getCustomerId(), orderDto.getProductIdsList())
        );
    }

    @GetMapping("/orders/{id}")
    ResponseEntity<Order> getOrder(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrder(id).get());
    }

    @GetMapping("/orders")
    ResponseEntity<List<Order>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PutMapping("/orders")
    ResponseEntity<Order> updateOrder(@RequestBody Order order){
        return ResponseEntity.ok(orderService.update(order));
    }

    @GetMapping("/orders/customer/{id}")
    ResponseEntity<List<Order>> getOrderByCustomerId(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderByCustomer(id));
    }
    @PutMapping("/orders/status")
    ResponseEntity<Order> updateOrderStatus(@RequestParam String status, @RequestParam Long orderId){
        return ResponseEntity.ok(orderService.updateOrderStatus(status, orderId));
    }

}
