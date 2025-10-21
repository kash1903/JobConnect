package com.example.jobConnect.Config;

import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class SwaggerConfig {
    
        @Bean
    public OpenAPI jobConnectOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("JobConnect API Documentation")
                        .description("API documentation for Job Portal Application")
                        .version("1.0"));
    }

}
