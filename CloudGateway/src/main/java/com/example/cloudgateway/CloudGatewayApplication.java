package com.example.cloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient
@EnableHystrix
public class CloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudGatewayApplication.class, args);
    }

    /*RouteLocator routeLocator(RouteLocatorBuilder builder){
        return  builder.routes()
                .route((r)-> (Buildable<Route>) r.path("/authenticate/**").uri("lb://MOVIE-SERVICE"))
                .route((r)-> (Buildable<Route>) r.path("/authenticate/**").uri("lb://AUTH-SERVICE"))
                .build();
    }*/
}
