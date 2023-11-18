package com.aks.order.mgmt.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.aks.order.mgmt.order.model.entity.Order;
import com.aks.order.mgmt.order.service.OrderService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Api(value="Order Microservice", description="Operations pertaining to Order in Order Management System")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	
	@PostMapping(value = "/order")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> createOrder(@RequestBody Order order) {
		return orderService.createOrder(order);
	}
	
	@GetMapping(value = "/order")
	public ResponseEntity<List<Order>> getAllOrder() {
		return new ResponseEntity<>(orderService.getAllOrder(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/order/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable  String id){
		return orderService.getOrderById(id)
		        .map((customerRes) -> new ResponseEntity<>(customerRes, HttpStatus.OK))
		        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	     
    }
	
	@DeleteMapping(value = "/order/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id){
        return new ResponseEntity<>(orderService.deleteOrder(id), HttpStatus.OK);
    }
	
	@PutMapping(value = "/order/{id}")
	public ResponseEntity<?> updateOrder(@PathVariable String id, @RequestBody Order order) {
			return new ResponseEntity<>(orderService.updateOrder(id, order), HttpStatus.OK);
	}
	
	

}
