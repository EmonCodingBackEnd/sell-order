package com.coding.sell;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.coding")
@SpringBootApplication
public class SellOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SellOrderApplication.class, args);
	}

}
