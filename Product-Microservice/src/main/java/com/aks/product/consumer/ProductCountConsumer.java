/**
 * 
 */
package com.aks.product.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.aks.product.model.ProductCountEventDTO;
import com.aks.product.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Service

public class ProductCountConsumer {
	
	@Autowired
    ObjectMapper objectMapper;
	
	@Autowired
	ProductService productService;
	
	
	@KafkaListener(topics = "${kafka.message.productTopic.name}", containerFactory = "productCountLimitKafkaListenerContainerFactory")
    public void exchangeEventListener(String productCountlimit) {
		
		try {
			ProductCountEventDTO productCountEventDTO = objectMapper.readValue(productCountlimit, ProductCountEventDTO.class);
			productService.updateProductCount(productCountEventDTO);
	       
	        
		} catch (JsonProcessingException e) {
			//log.error("Error Sending the Message and the exception is {}", e.getMessage());
		}
    }
}
