package com.fiscalliance.iam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceIamApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceIamApplication.class, args);
	}

}
