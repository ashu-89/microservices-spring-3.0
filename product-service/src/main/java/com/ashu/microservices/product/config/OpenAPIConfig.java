package com.ashu.microservices.product.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI ProductServiceAPI() {
        return new OpenAPI()
                .info(new Info().title("Product Service API")
                        .description("Product Service API implemented using Spring Boot RESTful service and documented using springdoc-openapi and OpenAPI 3.0")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Product Service Wiki Documentation")
                        .url("https://youtube.com/ashuzguitar"));
    }
}
