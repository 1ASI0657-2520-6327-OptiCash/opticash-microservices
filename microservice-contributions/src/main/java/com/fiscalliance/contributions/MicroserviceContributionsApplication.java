package com.fiscalliance.contributions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {
		"com.fiscalliance.contributions",
		"com.fiscalliance.shared"
})
@EnableJpaAuditing
public class MicroserviceContributionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceContributionsApplication.class, args);
	}

}
