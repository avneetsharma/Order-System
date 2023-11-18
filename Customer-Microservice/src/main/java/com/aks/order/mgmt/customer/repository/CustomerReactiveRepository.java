package com.aks.order.mgmt.customer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.aks.order.mgmt.customer.domain.entity.Customer;


//@Repository
@Repository
public interface CustomerReactiveRepository extends MongoRepository<Customer, String> {

}
