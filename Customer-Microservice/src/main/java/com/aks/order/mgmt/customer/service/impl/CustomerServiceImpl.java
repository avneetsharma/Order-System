package com.aks.order.mgmt.customer.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.aks.order.mgmt.customer.constant.Constants;
import com.aks.order.mgmt.customer.domain.dto.CustomerCreditLimitEventDTO;
import com.aks.order.mgmt.customer.domain.entity.Customer;
import com.aks.order.mgmt.customer.enums.ActionType;
import com.aks.order.mgmt.customer.repository.CustomerReactiveRepository;
import com.aks.order.mgmt.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	//@Qualifier("customerReactiveRepository")
	private CustomerReactiveRepository customerReactiveRepository;
	
	@Autowired
	ReactiveMongoTemplate reactiveMongoTemplate;

	private static final Logger logger = LogManager.getLogger(CustomerServiceImpl.class);

	@Override
	public Optional<Customer> saveCustomer(Customer customer) {
		return Optional.of(customerReactiveRepository.save(customer));
	}

	@Override
	public Optional<Customer> findCustomerById(String customerId) {
		return customerReactiveRepository.findById(customerId);
	}

	@Override
	public List<Customer> findAllCustomer() {
		return customerReactiveRepository.findAll();
	}

	@Override
	public Optional<Customer> updateCustomer(String customerId, Customer customer) {
		Optional<Customer> customerExist = customerReactiveRepository.findById(customerId);
		if (customerExist.isEmpty()) {
			customerReactiveRepository.save(customer);
		}
		Customer existing = customerExist.get();
		existing = customer;
		existing.setId(customerExist.get().getId());
		
		Query updateQuery = new Query();
		updateQuery.addCriteria(Criteria.where(Constants.CUSTOMER_ID_KEY).is(customerId));
		reactiveMongoTemplate.findAndReplace(updateQuery, customer, FindAndReplaceOptions.options().returnNew());
		return Optional.of(customer);
	}

	@Override
	public Optional<Customer> deleteCustomer(String customerId) {
		Optional<Customer> customerExist = customerReactiveRepository.findById(customerId);
		customerReactiveRepository.deleteById(customerId);
		return customerExist;
	}

	@Override
	public void updateCustomerCreditLimit(CustomerCreditLimitEventDTO customerCreditLimitEvent) {

		Optional<Customer> customerExist = customerReactiveRepository
				.findById(customerCreditLimitEvent.getCustomerId());
		if (!customerExist.isEmpty()) {
			Customer customer = customerExist.get();
			Double currentCreditLimit = customer.getCreditLimit();
			if (StringUtils.equals(ActionType.CREDIT_INCREMENT.name(),
					customerCreditLimitEvent.getActionType().name())) {
				customer.setCreditLimit(currentCreditLimit + customerCreditLimitEvent.getCreditLimitAmount());
			} else {
				customer.setCreditLimit(currentCreditLimit - customerCreditLimitEvent.getCreditLimitAmount());
			}
			customerReactiveRepository.save(customer);
		}

	}
}