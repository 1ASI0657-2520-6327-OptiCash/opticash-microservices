package com.fiscalliance.iam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {
        "com.fiscalliance.membercontributions",
        "com.fiscalliance.shared"
})
@EnableJpaAuditing
@EnableDiscoveryClient
public class MicroserviceMemberContributionsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceMemberContributionsApplication.class, args);
    }
}
