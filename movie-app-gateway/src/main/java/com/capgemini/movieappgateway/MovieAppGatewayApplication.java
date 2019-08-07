package com.capgemini.movieappgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
//The below annotation is not mandatory
@EnableDiscoveryClient
public class MovieAppGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieAppGatewayApplication.class, args);
	}

}
