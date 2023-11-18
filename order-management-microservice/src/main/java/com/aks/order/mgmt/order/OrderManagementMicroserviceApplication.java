package com.aks.order.mgmt.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@ComponentScan("com.aks.*")
public class OrderManagementMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementMicroserviceApplication.class, args);
	}

}
