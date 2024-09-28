package com.cst.sr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AiEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiEurekaServerApplication.class, args);
	}

}
