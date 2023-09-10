package com.shoe.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class OnlineShoeStoreApplication {
	public static void main(String[] args) {

		SpringApplication.run(OnlineShoeStoreApplication.class, args);
	}

}