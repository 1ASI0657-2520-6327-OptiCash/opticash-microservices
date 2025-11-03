package com.fiscalliance.opticash.households;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing
public class MicroserviceHouseHoldsApplication {
    public static void main(String[] args) {
        SpringApplication.run(MicroserviceHouseHoldsApplication.class, args);
    }
}
