package com.aks.order.mgmt.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aks.order.mgmt.order.model.entity.Order;

@Repository
public interface OrderReactiveRepository extends MongoRepository<Order, String> {
	
}
