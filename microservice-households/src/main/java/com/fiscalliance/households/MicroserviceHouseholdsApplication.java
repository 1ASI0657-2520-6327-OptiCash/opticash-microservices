package com.fiscalliance.households;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {
		"com.fiscalliance.households",
		"com.fiscalliance.shared"
})
@EnableJpaAuditing
@EnableDiscoveryClient
public class MicroserviceHouseholdsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceHouseholdsApplication.class, args);
	}

}
