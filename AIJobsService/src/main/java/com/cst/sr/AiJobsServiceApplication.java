package com.cst.sr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AiJobsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiJobsServiceApplication.class, args);
	}

}
