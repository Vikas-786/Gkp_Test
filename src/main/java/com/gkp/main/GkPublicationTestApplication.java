package com.gkp.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication (exclude = { SecurityAutoConfiguration.class })
public class GkPublicationTestApplication {

	public static void main(String[] args) {
				SpringApplication.run(GkPublicationTestApplication.class, args);
		
		
	}

}
