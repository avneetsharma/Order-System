package com.aks.order.mgmt.order.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.aks.order.mgmt.order.enums.ActionType;
import com.aks.order.mgmt.order.model.dto.Customer;
import com.aks.order.mgmt.order.model.dto.CustomerCreditLimitEventDTO;
import com.aks.order.mgmt.order.model.dto.Product;
import com.aks.order.mgmt.order.model.dto.ProductCountEventDTO;
import com.aks.order.mgmt.order.model.entity.Order;
import com.aks.order.mgmt.order.producer.ProductCustomerMsgProducer;
import com.aks.order.mgmt.order.repository.OrderReactiveRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderReactiveRepository orderReactiveRepository;

	

	@Autowired
	ProductCustomerMsgProducer productCustomerMsgProducer;
	
	@Autowired
	ProductCountEventDTO productCountEventDTO;
	
	@Autowired
	CustomerCreditLimitEventDTO customerCreditLimitEventDTO;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${customer.host.name}")
	private String customerHostName;

	@Value("${customer.host.url}")
	private String customerHostUrl;

	@Value("${product.host.name}")
	private String productHostName;

	@Value("${product.host.url}")
	private String productHostUrl;

	
	@Override
	public ResponseEntity<?> createOrder(Order order) {
		ResponseEntity<?> object =  validateRequest(order);
		if(null == object) {
			order = orderReactiveRepository.insert(order);
			initiateKafkaEventForCreate(order);
		    return new ResponseEntity<>(order, HttpStatus.OK);
		}
		return object; 
	}

	private ResponseEntity<?> validateRequest(Order order) {

		ResponseEntity<?> responseEntity = null;
		UriComponentsBuilder customerbuilderUrl = UriComponentsBuilder.fromHttpUrl(customerHostUrl)
				.path(customerHostName).path(order.getCustomerId());

		ResponseEntity<Customer> customer = restTemplate.getForEntity(customerbuilderUrl.toUriString(), Customer.class);

		UriComponentsBuilder productbuilderUrl = UriComponentsBuilder.fromHttpUrl(productHostUrl).path(productHostName)
				.path(order.getProduct().getProductId());
		ResponseEntity<Product> product = restTemplate.getForEntity(productbuilderUrl.toUriString(), Product.class);


		Customer customerObject = customer.getBody();
		Product productObject = product.getBody();

		Integer productQtyInStock = productObject.getQtyInStock();
		if (productQtyInStock < order.getProduct().getProductCount()) {
			responseEntity =  new ResponseEntity<>(
					"Due to limited stock, Maximum Order limit for this product is " + productQtyInStock,
					HttpStatus.BAD_REQUEST);
		}

		double currentCreditLimit = customerObject.getCreditLimit();
		if (currentCreditLimit < order.getOrderAmount()) {
			responseEntity = new  ResponseEntity<>("Customer does not have sufficient Credit limit to place the Order.",
					HttpStatus.BAD_REQUEST);
		}
		order.setOrderId(UUID.randomUUID().toString());

		 return responseEntity;

		
	}
	
	@Override
	public List<Order> getAllOrder() {
		return orderReactiveRepository.findAll();
	}

	@Override
	public Optional<Order> getOrderById(String orderId) {
		return orderReactiveRepository.findById(orderId);
	}
	
	@Override
	public Optional<Order> deleteOrder(String orderId) {
		final Optional<Order> order = getOrderById(orderId);
		if (order.isEmpty()) {
			return order.empty();
		}
		 orderReactiveRepository.deleteById(orderId);
		 initiateKafkaEventforDelete(order.get());
		 return order;
	}

	

	@Override
	public Optional<Order> updateOrder(String orderId, Order updated_order) {

		 validateRequest(updated_order);
		Optional<Order> order = getOrderById(orderId);
		Order return_updated_order = orderReactiveRepository.save(updated_order);
		initiateKafkaEventforUpdate(order.get(),updated_order, orderId);
		return Optional.of(return_updated_order);
	}

	

	/****************** Start :: intiate kafka event for create ******************/
	private void initiateKafkaEventForCreate(Order order) {

		productCountEventDTO.setOrderId(order.getOrderId());
		productCountEventDTO.setProductId(order.getProduct().getProductId());
		productCountEventDTO.setProductCount(order.getProduct().getProductCount());
		productCountEventDTO.setActionType(ActionType.COUNT_DECREMENT);

		customerCreditLimitEventDTO.setOrderId(order.getOrderId());
		customerCreditLimitEventDTO.setCustomerId(order.getCustomerId());
		customerCreditLimitEventDTO.setCreditLimitAmount(order.getOrderAmount());
		customerCreditLimitEventDTO.setActionType(ActionType.CREDIT_DECREMENT);

		try {
			productCustomerMsgProducer.sendToProduct(productCountEventDTO);
			productCustomerMsgProducer.sendToCustomer(customerCreditLimitEventDTO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/****************** End :: intiate kafka event for create ******************/
	
	/****************** Start :: intiate kafka event for update order ******************/
	private void initiateKafkaEventforUpdate(Order old_order, Order updated_order, String orderId) {

		/****************** Start :: Product Count Calculation ******************/
		if (old_order.getProduct().getProductCount() != updated_order.getProduct().getProductCount()) {
			
			productCountEventDTO.setOrderId(orderId);
			productCountEventDTO.setProductId(updated_order.getProduct().getProductId());

			if (old_order.getProduct().getProductCount() > updated_order.getProduct().getProductCount()) {
				int count_diff = (old_order.getProduct().getProductCount())
						- (updated_order.getProduct().getProductCount());
				productCountEventDTO.setProductCount(count_diff);
				productCountEventDTO.setActionType(ActionType.COUNT_INCREMENT);

			} else {
				int count_diff = (updated_order.getProduct().getProductCount())
						- (old_order.getProduct().getProductCount());
				productCountEventDTO.setProductCount(count_diff);
				productCountEventDTO.setActionType(ActionType.COUNT_DECREMENT);
			}
			try {
				productCustomerMsgProducer.sendToProduct(productCountEventDTO);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		/****************** End :: Product Count Calculation ******************/

		/****************** Start :: Customer Credit Limit Calculation ******************/
		
		if (old_order.getOrderAmount() != updated_order.getOrderAmount()) {
			
			customerCreditLimitEventDTO.setOrderId(orderId);
			customerCreditLimitEventDTO.setCustomerId(updated_order.getCustomerId());

			if (old_order.getOrderAmount() > updated_order.getOrderAmount()) {
				double credit_diff = (old_order.getOrderAmount()) - (updated_order.getOrderAmount());
				customerCreditLimitEventDTO.setCreditLimitAmount(credit_diff);
				customerCreditLimitEventDTO.setActionType(ActionType.CREDIT_INCREMENT);
			} else {
				double credit_diff = (updated_order.getOrderAmount()) - (old_order.getOrderAmount());
				customerCreditLimitEventDTO.setCreditLimitAmount(credit_diff);
				customerCreditLimitEventDTO.setActionType(ActionType.CREDIT_DECREMENT);

			}
			try {

				productCustomerMsgProducer.sendToCustomer(customerCreditLimitEventDTO);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

		}

		/****************** End :: Credit Limit Calculation ******************/

	}
	/****************** End :: intiate kafka event for update order ******************/
	
	/****************** Start :: intiate kafka event for delete ******************/
	private void initiateKafkaEventforDelete(Order order) {

		productCountEventDTO.setOrderId(order.getOrderId());
		productCountEventDTO.setProductId(order.getProduct().getProductId());
		productCountEventDTO.setProductCount(order.getProduct().getProductCount());
		productCountEventDTO.setActionType(ActionType.COUNT_INCREMENT);

		customerCreditLimitEventDTO.setOrderId(order.getOrderId());
		customerCreditLimitEventDTO.setCustomerId(order.getCustomerId());
		customerCreditLimitEventDTO.setCreditLimitAmount(order.getOrderAmount());
		customerCreditLimitEventDTO.setActionType(ActionType.CREDIT_INCREMENT);

		try {
			productCustomerMsgProducer.sendToProduct(productCountEventDTO);
			productCustomerMsgProducer.sendToCustomer(customerCreditLimitEventDTO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	/****************** End :: intiate kafka event for delete ******************/

}
