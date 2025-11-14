package com.fiscalliance.settings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication(scanBasePackages = {
		"com.fiscalliance.settings",
		"com.fiscalliance.shared"
})
@EnableJpaAuditing
public class MicroserviceSettingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceSettingsApplication.class, args);
	}

}
