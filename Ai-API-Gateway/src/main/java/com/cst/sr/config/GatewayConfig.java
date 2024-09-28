package com.cst.sr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cst.sr.filter.AuthenticationFilter;

@Configuration
public class GatewayConfig {
	
	@Autowired
	AuthenticationFilter filter;


	@Bean
	public RouteLocator MyRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				
				.route("userprofileservice", p -> p.path("/api/ups/**")
				.filters(f -> f.filter(filter))
				.uri("lb://UserProfileService"))

				.route("aiauthenticationservice", p -> p.path("/api/auth/**")
				.uri("lb://AIAuthenticationService"))
				
				.route("aijobsService", p -> p.path("/v1/AIJob/**")
				.filters(f -> f.filter(filter))
				.uri("lb://AIJobsService"))

				.route("aibookmarkservice", p -> p.path("/v1/bs/**")
				.filters(f -> f.filter(filter))
				.uri("lb://AIBookmarkService"))
				.build();
	}
//	@Bean
//    public RouteLocator MyRoutes2(RouteLocatorBuilder builder)
//    {
//        return builder.routes()
//        		.route("api/v1", r -> r.path("/api/v1/**")
//						.uri("lb://AUTHENTICATIONSERVICE"))
//                .route("api/v2", r -> r.path("/api/v2/**")
//						.uri("lb://USERPROFILESERVICE"))
//                .route("api/v3", r -> r.path("/api/v3/**")
//                		.filters(f -> f.filter(filter))
//						.uri("lb://RECOMMENDATIONSERVICE"))                
//                .route("api/v4", r -> r.path("/api/v4/**")
//                		.filters(f -> f.filter(filter))
//						.uri("lb://PERFORMANCESERVICE"))                
//                .build();
//
//    }


}
