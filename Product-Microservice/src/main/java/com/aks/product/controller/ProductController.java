package com.aks.product.controller;



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

import com.aks.product.model.Product;
import com.aks.product.service.ProductService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/product")

public class ProductController {
	
	@Autowired
	private ProductService productService;


	@PostMapping(value = "/")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Product> addProduct(@RequestBody Product product) {
		return productService.addproduct(product);

	}

	
	@GetMapping(value = "/")
	public Flux<Product> getAllProduct() {
		return productService.getAllProduct();
	}

	
	@GetMapping(value = "/{id}")
	public Mono<ResponseEntity<Product>> getProductById(@PathVariable("id") final String id) {
		System.out.println("*****************************");
		return productService.getProductById(id).map((product) -> new ResponseEntity<>(product, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	
	@PutMapping(value = "/{id}")
	public Mono<Product> updateProduct(@PathVariable("id") final String id, @RequestBody Product product) {

		return productService.updateProduct(id, product);
	}

	@DeleteMapping("/{id}")
	public Mono<Product> deleteProduct(@PathVariable final String id) {

		return productService.deleteProduct(id);
	}

	
}