package com.mrseams.apigateway.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class APiDocConfig {

    @Bean
    public OpenAPI inventoryServiceApiDoc() {
        return new OpenAPI()
                .info(new Info().title("Inventory Service API")
                        .version("v1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
                );
    }
}