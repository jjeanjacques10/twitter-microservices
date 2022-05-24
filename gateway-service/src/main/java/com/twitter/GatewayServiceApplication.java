package com.twitter;

import com.twitter.config.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
@EnableAutoConfiguration
public class GatewayServiceApplication {

    @Autowired
    private JwtAuthenticationFilter filter;


    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r
                        .path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://USER-SERVICE"))
                .route(r -> r
                        .path("/user/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://USER-SERVICE"))
                .route(p -> p
                        .path("/tweet/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://TWEET-SERVICE"))
                .build();
    }
}
