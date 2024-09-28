package com.cst.sr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AiApiGatewayApplication {

	//http://vm-lab39-20-320.asia-south1-b.c.niit-stackroute-2.internal:9000/api/ups/UserProfileById/1
	public static void main(String[] args) {
		SpringApplication.run(AiApiGatewayApplication.class, args);
	}

}
