package com.aks.order.mgmt.customer.service;

import java.util.List;
import java.util.Optional;

import com.aks.order.mgmt.customer.domain.dto.CustomerCreditLimitEventDTO;
import com.aks.order.mgmt.customer.domain.entity.Customer;


public interface CustomerService {

	Optional<Customer> saveCustomer(Customer customer);
	
	Optional<Customer> findCustomerById(String customerId);
	
	
	List<Customer> findAllCustomer();
	
	
	Optional<Customer> updateCustomer(String customerId, Customer customer);
	
	
	Optional<Customer> deleteCustomer(String customerId);

	
	void updateCustomerCreditLimit(CustomerCreditLimitEventDTO customerCreditLimitEvent);
}
