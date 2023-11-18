package com.aks.order.mgmt.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.aks.order.mgmt.order.model.entity.Order;

public interface OrderService {
	
    public ResponseEntity<?> createOrder(Order order);
    public List<Order> getAllOrder();
    public Optional<Order> getOrderById(String orderId);
    public Optional<Order> deleteOrder(String orderId);
    public Optional<Order> updateOrder(String orderId, Order order);

}
