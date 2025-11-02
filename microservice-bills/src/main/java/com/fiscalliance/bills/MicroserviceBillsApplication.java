package com.fiscalliance.bills;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableFeignClients(basePackages = "com.fiscalliance.bills.infrastructure.clients")

@SpringBootApplication(scanBasePackages = {
		"com.fiscalliance.bills",
		"com.fiscalliance.shared"
})
@EnableJpaAuditing
@EnableDiscoveryClient
public class MicroserviceBillsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceBillsApplication.class, args);
	}

}
