package com.mrseams.apigateway.routes;

import org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;

@Configuration
@CrossOrigin("*")
public class Routes {
    @Bean

    public RouterFunction<ServerResponse> productServiceRoute(){
        return route("product_service")
                .route(RequestPredicates.GET("/api/products"), HandlerFunctions.http("http://localhost:8080/"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceCircuitBreakerId", URI.create("forward://fallback")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceRoute(){
        return route("order_service")
                .route(RequestPredicates.path("/api/orders"), HandlerFunctions.http("http://localhost:8081/"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("orderServiceCircuitBreakerId", URI.create("forward://fallback")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceRoute(){
        return route("inventory_service")
                .route(RequestPredicates.GET("/api/inventory"), HandlerFunctions.http("http://localhost:8082/"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("inventoryServiceCircuitBreakerId", URI.create("forward://fallback")))
                .build();
    }


    // open api doc services routes

    @Bean
    public RouterFunction<ServerResponse> productServiceApiDocsRoutes(){
        return route("product_service_swagger")
                .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8080"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("productServiceDocsCircuitBreakerId", URI.create("forward://fallback")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceApiDocsRoutes(){
        return route("order_service_swagger")
                .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8081"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("orderServiceDocsCircuitBreakerId", URI.create("forward://fallback")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceApiDocsRoutes(){
        return route("inventory_service_swagger")
                .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), HandlerFunctions.http("http://localhost:8082"))
                .filter(CircuitBreakerFilterFunctions.circuitBreaker("inventoryServiceDocsCircuitBreakerId", URI.create("forward://fallback")))
                .filter(setPath("/api-docs"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> fallbackRoute(){
        return route("fallback-route")
                .GET("/fallback", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE)
                        .body("Service Unavailable, please try again later"))
                .build();
    }
}
