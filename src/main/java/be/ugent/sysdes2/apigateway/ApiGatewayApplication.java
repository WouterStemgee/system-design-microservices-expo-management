package be.ugent.sysdes2.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				// Event Reservation service routes
				.route(r -> r.host("*").and().path("/reservation/**").uri("http://service-event-reservation:2120"))
				// Event Management service routes
				.route(r -> r.host("*").and().path("/management/**").uri("http://service-event-management:2121"))
				// Parking service routes
				.route(r -> r.host("*").and().path("/parking/**").uri("http://service-parking:2122"))
				// Ticket service routes
				.route(r -> r.host("*").and().path("/ticket/**").uri("http://service-ticket:2123"))
				// Badge service routes
				.route(r -> r.host("*").and().path("/badge/**").uri("http://service-badge:2124"))
				// Food-and-drinks service routes
				.route(r -> r.host("*").and().path("/food-and-drinks/**").uri("http://service-food-and-drinks:2125"))
				// Cloakroom service routes
				.route(r -> r.host("*").and().path("/cloakroom/**").uri("http://service-cloakroom:2126"))
				// Multimedia service routes
				.route(r -> r.host("*").and().path("/multimedia/**").uri("http://service-multimedia:2127"))
				// Security service routes
				.route(r -> r.host("*").and().path("/security/**").uri("http://service-security:2128"))
				.build();
	}
}
