package com.example.ProductMenager_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackages = {
				"com.example.ProductMenager_api",
				"com.example.ProductMenager_service",
				"com.example.ProductMenager_data"
		}
)
public class ProductMenagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMenagerApiApplication.class, args);
	}

}
