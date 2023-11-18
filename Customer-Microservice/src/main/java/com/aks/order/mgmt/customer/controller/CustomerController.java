package com.aks.order.mgmt.customer.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.aks.order.mgmt.customer.domain.entity.Customer;
import com.aks.order.mgmt.customer.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/customers")
@Api(value="Customer Microservice")
public class CustomerController {

	private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerService customerService;
	
	@ApiOperation(value = "This API is responsible to retrieve the list of all available Customers", response = List.class)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved list", response = Customer.class),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(value = "/")
	public ResponseEntity<?> findAllCustomer(){
		
		LOG.debug("Get all Customers call initiated at : [" + new Date() + "]");
		List<Customer> allCust = customerService.findAllCustomer();
		return new ResponseEntity<>(allCust, HttpStatus.OK);
    }
	
	
	@ApiOperation(value = "This API is responsible to retrieve a customer details by Customer Id")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved customer details", response = Customer.class),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@GetMapping(value = "/{id}")
    public ResponseEntity<?> findCustomerById(@PathVariable String id){
		
		return customerService.findCustomerById(id)
        .map((customerRes) -> new ResponseEntity<>(customerRes, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
       
    }
	
	
	@ApiOperation(value = "This API is responsible to create a new Customer")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Successfully created the new customer", response = Customer.class),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
	@PostMapping(value = "/")
    @ResponseStatus(HttpStatus.CREATED)
    public Optional<Customer> saveCustomer(@RequestBody Customer customer){
		
		LOG.debug("Create Customer call initiated at [" + new Date() + "] with request payload: " 
						+ customer.toString());
        return customerService.saveCustomer(customer);
    }
	
	@ApiOperation(value = "This API is responsible to update the details of an existing Customer")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully updtaed the existing customer", response = Customer.class),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @PutMapping(value = "/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String id,
                                                 @RequestBody Customer customer){
		
		LOG.debug("Update Customer[Customer Id: " + id + "] call initiated at [" + new Date() + "] with request payload: " 
				+ customer.toString());
		
        return customerService.updateCustomer(id, customer)
                .map((customerRes) -> new ResponseEntity<>(customerRes, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


	@ApiOperation(value = "This API is responsible to delete an existing Customer")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully deleted the existing customer", response = Customer.class),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable String id){
		
		LOG.debug("Delete Customer by Customer Id[" + id + "] call initiated at : [" + new Date() + "]");
		
        return customerService.deleteCustomer(id)
                .map((customer) -> new ResponseEntity<>(customer, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}