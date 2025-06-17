package com.manage.productmanage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                    .components(new Components())
                    .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("product API Docs")
                .description("결제 관련 모의 REST API")
                .version("1.0.0");
    }
}
